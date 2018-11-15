package com.messji.messji;



import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.util.List;

public class Database {
    public static class Messages {
        public List<Message> messages;

        public Messages(Context context) {
            // Read in messages from JSON file
            File file = new File(context.getFilesDir(), "messages");
            String mes = new Gson().toJson(messages);
            messages = new Gson().fromJson(file, Messages);
            Log.d("Found Messages:", mes);


        }
    }
    public List<Conversation> conversations;
    public List<User> users;


}
