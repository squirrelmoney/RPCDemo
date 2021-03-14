package service;

import java.io.IOException;

/**
 * @program: RPCDemo
 * @description:
 * @author: money
 * @create: 2021-03-11 13:56
 **/
public interface server {
     void stop();

     void start() throws IOException;

     void register(Class serviceInterface, Class impl);

     boolean isRunning();

     int getPort();
}
