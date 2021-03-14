import service.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: RPCDemo
 * @description:
 * @author: money
 * @create: 2021-03-12 15:25
 **/
public class start {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable worker = () -> {
            try {
                server serviceServer = new ServiceCenterForOriginal(8088);
                serviceServer.register(HelloService.class, HelloServiceImpl.class);
                serviceServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        executor.execute(worker);
    }
}
