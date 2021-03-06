package com.messji.messji;

public class Message {
    private int message_id;   // id associated with users message
    private int user_id;      //id associated with the actual user
    private String timestamp;
    private String text;      // message body

    // Constructor
    public Message(String text, int message_id, int user_id, boolean belongsToCurrentUser) {
        this.text = text;
        this.timestamp = "Timestamp";
        this.message_id = message_id;
        this.user_id = user_id;
    }

    public Message(String text, int user_id) {
        this.text = text;
        this.timestamp = "Timestamp";
        this.user_id = user_id;
    }

    public String getDate() {
        return timestamp;
    }

    public void setDate(String date) {
        this.timestamp = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
