package com.messji.messji;

public class User {
    public int avatar;
    public String name;
    public Message lastMessage;

    public User(int avatar, String name, Message lastMessage) {
        this.avatar = avatar;
        this.name = name;
        this.lastMessage = lastMessage;
    }
}