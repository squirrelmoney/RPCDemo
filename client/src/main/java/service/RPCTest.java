package service;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: RPCDemo
 * @description:
 * @author: money
 * @create: 2021-03-11 14:28
 **/
public class RPCTest {

//    public static void main(String[] args) throws IOException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        Runnable worker = new Runnable() {
//            @Override
//            public void run() {
//                HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
//                service.sayHi("service");
//                System.out.println(service.sayHi("service"));
//            }
//        };
//        while(true){
//            Thread.sleep(10 * 1000);
//            executor.execute(worker);
//        }
//
//    }
public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    Runnable worker = new Runnable() {
        @Override
        public void run() {
            HelloService service = RPCClientForOriginal.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
            List service1 = service.sayHi2("service");
//            System.out.println(service.sayHi2("service"));
        }
    };
    while(true){
        Thread.sleep(10 * 1000);
        executor.execute(worker);
    }

}
}
