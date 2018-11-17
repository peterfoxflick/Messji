package com.messji.messji;



import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Database {

    public static class Messages {
        public static List<Message> messages;

        public Messages() {
            // Read in messages from JSON file
            Gson gson = new Gson();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("messages.json"));
                messages = gson.fromJson(bufferedReader, messages.getClass());
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
        }
    }

    public static class Conversations {
        public static List<Conversation> conversations;

        public Conversations() {
            // Read in messages from JSON file
            Gson gson = new Gson();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("conversations.json"));
                conversations = gson.fromJson(bufferedReader, conversations.getClass());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        public void save() {
            String mes = new Gson().toJson(conversations);
            try {
                Writer output = new BufferedWriter(new FileWriter("conversations.json"));
                output.write(mes);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static class Users {
        public static List<User> users;

        public Users() {
            // Read in messages from JSON file
            Gson gson = new Gson();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("contacts.json"));
                users = gson.fromJson(bufferedReader, users.getClass());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        public void save() {
            String mes = new Gson().toJson(users);
            try {
                Writer output = new BufferedWriter(new FileWriter("contacts.json"));
                output.write(mes);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
