package service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: RPCDemo
 * @description:
 * @author: money
 * @create: 2021-03-11 16:50
 **/

public class JsonSerialize {
    public static void main(String[] args) throws IOException {
        new JsonSerialize().start();
    }

    public void start() throws IOException {
        User u = new User();
        List<User> friends = new ArrayList();
        u.setUserName("张三");
        u.setPassWord("123456");
        u.setUserInfo("张三是一个很牛逼的人");
        u.setFriends(friends);

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
        String resultJson = JSONObject.toJSONString(u);
        ObjectMapper mapper = new ObjectMapper();
        Long t1 = System.currentTimeMillis();
        byte[] writeValueAsBytes = null;
        writeValueAsBytes = mapper.writeValueAsBytes(resultJson);
        System.out.println("json格式序列化: " + (System.currentTimeMillis() - t1) + "ms; 总大小：" + writeValueAsBytes.length);
        Long t2 = System.currentTimeMillis();
        String s = mapper.readValue(writeValueAsBytes, String.class);
        System.out.println("json格式反序列化: " + (System.currentTimeMillis() - t2) + "ms; ");

    }
}
