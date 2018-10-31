package com.messji.messji;


import java.util.Date;

public class Message {
    private String text; // message body
    private User data; // data of the user that sent this message
    private boolean belongsToCurrentUser; // is this message sent by us?

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Message(String text, User data, boolean belongsToCurrentUser) {
        this.text = text;
        this.data = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
        this.date = new Date();
    }

    public String getText() {
        return text;
    }

    public User getData() {
        return data;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
