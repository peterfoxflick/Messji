package com.messji.messji;



import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public final class Database {
    private static List messages;
    private static List conversations;
    private static User[] users;

    public static List getMessages() {
        return messages;
    }

    public static List getConversations() {
        return conversations;
    }

    public static User[] getUsers() {
        return users;
    }

    public Database(){
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("messages.json"));
            messages = gson.fromJson(bufferedReader, messages.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("conversations.json"));
            conversations = gson.fromJson(bufferedReader, conversations.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("contacts.json"));
            users = gson.fromJson(bufferedReader, users.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void loadUsers() {
        Gson gson = new Gson();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("contacts.json"));
            users = gson.fromJson(bufferedReader, users.getClass());
            Log.i("Users: ", Arrays.toString(users));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void save() {
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

        mes = new Gson().toJson(users);
        try {
            Writer output = new BufferedWriter(new FileWriter("contacts.json"));
            output.write(mes);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
