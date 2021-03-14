package service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.*;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @program: RPCDemo
 * @description:
 * @author: money
 * @create: 2021-03-11 13:58
 **/
public class ServiceCenter implements server {
    //获取系统处理器个数，作为线程池数量 Runtime.getRuntime().availableProcessors():返回可用处理器的Java虚拟机的数量
    private static int nThreads = Runtime.getRuntime().availableProcessors();
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    //线程池数量
    private static ExecutorService executor = new ThreadPoolExecutor(nThreads, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    private static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();

    private static boolean isRunning = false;

    private static int port;

    public ServiceCenter(int port) {
        ServiceCenter.port = port;
    }

    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    //获取连接
    public void start() throws IOException {
        ServerSocket server = new ServerSocket();
        //捆绑端口来提供服务
        server.bind(new InetSocketAddress(port));
        System.out.println("start server");
        try {
            while (true) {
                // 1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
                /**
                 * accept() ：会从上述已建立连接套接字队列，获取队列头部的socket，来进行处理，
                 * 每次accept()都会返回一个对应客户端的全新socket。
                 * 当建立连接队列为空时，accept会阻塞并处于睡眠状态
                 */
                executor.execute(new ServiceTask(server.accept()));
            }
        } finally {
            server.close();
        }
    }

    //注册接口与实现类
    @Override
    public void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPort() {
        return port;
    }

    private static class ServiceTask implements Runnable {
        Socket clent = null;

        public ServiceTask(Socket client) {
            this.clent = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
           // ObjectOutputStream output = null;
            OutputStream output = null;
            try {
                // 2.将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
                input = new ObjectInputStream(clent.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class serviceClass = serviceRegistry.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);

                // 3.将执行结果序列化，通过socket发送给客户端
                //序列化
                String resultJson = JSONObject.toJSONString(result);
                Long t1 = System.currentTimeMillis();
                ObjectMapper mapper = new ObjectMapper();
                byte[] writeValueAsBytes = null;
                writeValueAsBytes = mapper.writeValueAsBytes(resultJson);
//                output = new ObjectOutputStream(clent.getOutputStream());
//                output.writeObject(result);
                //输出流
                 output = clent.getOutputStream();
                //写出数据
                output.write(writeValueAsBytes);
                System.out.println("json格式序列化: " + (System.currentTimeMillis() - t1) + "ms; 总大小：" + writeValueAsBytes.length);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (clent != null) {
                    try {
                        clent.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
