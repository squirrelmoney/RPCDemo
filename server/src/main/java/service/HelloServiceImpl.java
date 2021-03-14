package service;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: RPCDemo
 * @description:
 * @author: dada
 * @create: 2021-03-11 13:55
 **/
public class  HelloServiceImpl implements HelloService{

    @Override
    public List<User> sayHi(String name) {
        User u = new User();
        List<User> friends = new ArrayList();
        User f1 = new User();
        f1.setUserName("李四");
        f1.setPassWord("123456");
        f1.setUserInfo("李四是一个很牛逼的人");

        User f2 = new User();
        f2.setUserName("王五");
        f2.setPassWord("123456");
        f2.setUserInfo("王五是一个很牛逼的人");

        friends.add(f1);
        friends.add(f2);

        u.setUserName("张三");
        u.setPassWord("123456");
        u.setUserInfo("张三是一个很牛逼的人");
        u.setFriends(friends);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            arrayList.add(u);
        }
        return arrayList;
    }

    @Override
    public List<User> sayHi2(String name) {
        User u = new User();
        List<User> friends = new ArrayList();
        User f1 = new User();
        f1.setUserName("李四");
        f1.setPassWord("123456");
        f1.setUserInfo("李四是一个很牛逼的人");

        User f2 = new User();
        f2.setUserName("王五");
        f2.setPassWord("123456");
        f2.setUserInfo("王五是一个很牛逼的人");

        friends.add(f1);
        friends.add(f2);

        u.setUserName("张三");
        u.setPassWord("123456");
        u.setUserInfo("张三是一个很牛逼的人");
        u.setFriends(friends);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            arrayList.add(u);
        }
        return arrayList;
    }
}
