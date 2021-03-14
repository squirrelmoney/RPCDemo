package service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: RPCDemo
 * @description:
 * @author: money
 * @create: 2021-03-11 15:49
 **/
public class JavaSerialize {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new JavaSerialize().start();
    }

    public void start() throws IOException, ClassNotFoundException {
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


        Long t1 = System.currentTimeMillis();
        //序列化
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(out);
        obj.writeObject(u);
        System.out.println( " 总大小：" + out.toByteArray().length );
        System.out.println("序列化所需时间: " +(System.currentTimeMillis() - t1)+"ms;" );

        Long t2 = System.currentTimeMillis();
        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new java.io.ByteArrayInputStream(out.toByteArray())));
        User user = (User) ois.readObject();
        System.out.println("反序列化所需时间: " + (System.currentTimeMillis() - t2)+"ms;" );
    }

}
