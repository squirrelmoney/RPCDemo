package service;

import java.io.Serializable;
import java.util.List;

/**
 * @program: RPCDemo
 * @description:
 * @author: money
 * @create: 2021-03-11 15:50
 **/

public class User implements Serializable {
    private  String userName;
    private  String passWord;
    private  String userInfo;
    private List<User> friends;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "model.User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", friends=" + friends +
                '}';
    }
}
