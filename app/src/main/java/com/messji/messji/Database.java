package com.messji.messji;



import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Database {
    private static List messages;
    private static List conversations;
    private static List users;

    public static List getMessages() {
        return messages;
    }

    public static List getConversations() {
        return conversations;
    }

    public static List getUsers() {
        return users;
    }

    public static void loadUsers(Context context) {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.contacts)));
        users = gson.fromJson(bufferedReader, new TypeToken<List<User>>(){}.getType());
        Log.i("Users: ", users.toString());
    }

    public static void loadMessages(Context context) {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.messages)));
        messages = gson.fromJson(bufferedReader, new TypeToken<List<Message>>(){}.getType());
        Log.i("Messages: ", messages.toString());
    }

    public static void loadConversations(Context context) {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.conversations)));
        conversations = gson.fromJson(bufferedReader, new TypeToken<List<Conversation>>(){}.getType());
        Log.i("Conversations: ", conversations.toString());
    }

    public void save(Context context) {
        String mes = new Gson().toJson(messages);
        try {
            Writer output = new BufferedWriter(new FileWriter("messages.json"));
            output.write(mes);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mes = new Gson().toJson(conversations);
        try {
            Writer output = new BufferedWriter(new FileWriter("conversations.json"));
            output.write(mes);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //This is an atempt to write, it dosn't work
//        mes = new Gson().toJson(users);
//        Gson gson = new Gson();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.contacts)));
//        users = gson.fromJson(bufferedReader, new TypeToken<List<User>>(){}.getType());
//        Log.i("Users: ", users.toString());
    }


}
