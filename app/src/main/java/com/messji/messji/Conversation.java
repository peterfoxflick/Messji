package com.messji.messji;

import java.util.List;

public class Conversation {
   int id;
   private List<Integer> messages;
   String title;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getMessages() {
        return messages;
    }

    public void setMessages(List<Integer> messages) {
        this.messages = messages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void addMessage(int id){
        messages.add(id);
    }

    public String getLastUpdate(){
        if (messages != null && !messages.isEmpty()) {
            Integer lastId = messages.get(messages.size()-1);
            //Get message by id with id == lastId
            //Return the timestamp of the last mes
        }

        return "";
    }

}