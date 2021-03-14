package service;


import java.util.List;

/**
 * @program: RPCDemo
 * @description:
 * @author: dada
 * @create: 2021-03-11 13:54
 **/
public interface HelloService<T> {
    Object sayHi(String name);

    List<User> sayHi2(String name);

}

