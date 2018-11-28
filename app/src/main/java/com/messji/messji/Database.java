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

/**
 * Acts as a database for the app storing a list of messages, conversations, and users.
 * The information is initially pulled from a starting file, and then stored as a
 * shared preference.
 */
public final class Database {
    private static List messages;
    private static List conversations;
    private static List users;

    /**
     * Returns a list of all messages
     */
    public static List getMessages() {
        return messages;
    }

    /**
     * Returns a list of all conversations
     */
    public static List getConversations() {
        return conversations;
    }

    /**
     * Returns the list of all users (contacts lists)
     * @return List of Users
     */
    public static List getUsers() {
        return users;
    }

    /**
     * Pulls the users from the users.json file. This acts as an inital list of users for the
     * app
     * @param context Nesccessary to find the file. From an activity just pass in "this"
     */
    public static void loadUsers(Context context) {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.contacts)));
        users = gson.fromJson(bufferedReader, new TypeToken<List<User>>(){}.getType());
        Log.i("Users: ", users.toString());
    }

    /**
     * Pulls the messages from the messages.json file. This will act as an intial set of messages for
     * the app.
     * @param context Nesccessary to find the file. From an activity just pass in "this"
     */
    public static void loadMessages(Context context) {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.messages)));
        messages = gson.fromJson(bufferedReader, new TypeToken<List<Message>>(){}.getType());
        Log.i("Messages: ", messages.toString());
    }

    /**
     * Pulls the conversations from the conversation.json file. This will act as an intial set of conversations for
     * the app.
     * @param context Nesccessary to find the file. From an activity just pass in "this"
     */
    public static void loadConversations(Context context) {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.conversations)));
        conversations = gson.fromJson(bufferedReader, new TypeToken<List<Conversation>>(){}.getType());
        Log.i("Conversations: ", conversations.toString());
    }

    /**
     * Saves the new information added to the database so it can be used next time the app is launched
     */
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


    }

    // Class to write messages back into the Database class?

    /**
     * Adds a message to the database so it can be stored for future use.
     * @param message The message to save
     */
    public static void addMessage(Message message) {
        try {
            Writer output = new BufferedWriter(new FileWriter("messages.json"));
            output.write(message.getText());
            output.close();
            messages.add(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
