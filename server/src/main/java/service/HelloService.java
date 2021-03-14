package service;


import model.User;

import java.util.List;

/**
 * @program: RPCDemo
 * @description:
 * @author: dada
 * @create: 2021-03-11 13:54
 **/
public interface HelloService {
    List<User> sayHi(String name);

    List<User> sayHi2(String name);
}

