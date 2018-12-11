package com.messji.messji;



import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.messji.messji.ContactPackage.Contact;
import com.messji.messji.ConversationPackage.Conversation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Acts as a database for the app storing a list of messages, conversations, and contacts.
 * The information is initially pulled from a starting file, and then stored as a
 * shared preference.
 */
public final class Database {
    private static List<Message> messages;
    private static List<Conversation> conversations;
    private static List<Contact> contacts;
    private static DailyCount charCount;

    public static Integer getCharCount() {
        //loadCharCount(context);
        return charCount.getTodayCount();
    }

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
     * Returns the list of all contacts (contacts lists)
     * @return List of Users
     */
    public static List getContacts() {
        return contacts;
    }

    /**
     * Pulls the contacts from the contacts.json file. This acts as an inital list of contacts for the
     * app
     * @param context Nesccessary to find the file. From an activity just pass in "this"
     */
    public static void loadUsers(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Database", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        if(sharedPreferences.contains("Users")){
            String data = sharedPreferences.getString("Users", "");
            contacts = gson.fromJson(data, new TypeToken<List<Contact>>(){}.getType() );
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.contacts)));
            contacts = gson.fromJson(bufferedReader, new TypeToken<List<Contact>>(){}.getType());
        }

        Log.i("Loaded Users: ", contacts != null ? contacts.toString() : null);

    }

    /**
     * Pulls the messages from the messages.json file. This will act as an intial set of messages for
     * the app.
     * @param context Nesccessary to find the file. From an activity just pass in "this"
     */
    public static void loadMessages(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Database", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        if(sharedPreferences.contains("Messages")){
            String data = sharedPreferences.getString("Messages", "");
            messages = gson.fromJson(data, new TypeToken<List<Message>>(){}.getType() );
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.messages)));
            messages = gson.fromJson(bufferedReader, new TypeToken<List<Message>>(){}.getType());
        }

        Log.i("Loaded Messages: ", messages != null ? messages.toString() : null);

    }

    /**
     * Pulls the conversations from the conversation.json file. This will act as an intial set of conversations for
     * the app.
     * @param context Nesccessary to find the file. From an activity just pass in "this"
     */
    public static void loadConversations(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Database", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        if(sharedPreferences.contains("Conversations")){
            String data = sharedPreferences.getString("Conversations", "");
            conversations = gson.fromJson(data, new TypeToken<List<Conversation>>(){}.getType() );
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.contacts)));
            conversations = gson.fromJson(bufferedReader, new TypeToken<List<Conversation>>(){}.getType());
        }

        Log.i("Loaded Conversations: ", conversations != null ? conversations.toString() : null);

    }

    public static Integer loadCharCount(Context context) {
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences("Database", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        if(sharedPreferences.contains("CharCount")){
            String data = sharedPreferences.getString("CharCount", "0");
            charCount = gson.fromJson(data, DailyCount.class);
        } else {
            charCount = new DailyCount();
            charCount.setCount(5);
        }

        return charCount.getTodayCount();
    }

    public static void loadDatabase(Context context) {
        loadMessages(context);
        loadConversations(context);
        loadUsers(context);
        loadCharCount(context);

    }

    /**
     * Saves the new information added to the database so it can be used next time the app is launched
     */
    public void save(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Database", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //First save messages
        String data = new Gson().toJson(messages);
        editor.putString("Messages", data);

        //Second save conversations
        data = new Gson().toJson(conversations);
        editor.putString("Conversations", data);

        //Third save contacts
        data = new Gson().toJson(contacts);
        editor.putString("Users", data);

        data = new Gson().toJson(charCount);
        editor.putString("CharCount", data);

        editor.apply();

    }

    // More of the interaction part
    /**
     * Adds a message to the database so it can be stored for future use.
     * Note: Needs to still add to conversation list
     * @param message The message to save
     */
    public static void addMessage(Message message, Integer conversationId) {
        Conversation conversation = getConversationFromId(conversationId);
        int id = messages.size();
        message.setMessage_id(id);
        //conversation.addMessage(conversationId); -- causes crash
        Log.v("addMessage:", "Message size (before) is: " + messages.size());
        messages.add(message);
        Log.v("addMessage:", "Message size (after) is: " + messages.size());
    }

    /**
     * gets a conversation from the database
     * @param id the id of the conversation to retrive
     * @return the conversation or null if not found
     */
    public static Conversation getConversationFromId(Integer id){
        for(Conversation c: conversations){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }

    /**
     * retrieves a list of messages that are in a conversation
     * @param conversation the conversation object to pull messages from
     * @return the list of messages in that conversation
     */
    public static List<Message> getMessagesFromConversation(Conversation conversation) {
        List<Message> results = new ArrayList<Message>();

        if(conversation != null) {
            List<Integer> messageId = conversation.getMessages();

            for (Message m : messages) {
                if (messageId.contains(m.getMessage_id())) {
                    results.add(m);
                }
            }

            return results;
        }
        return null;
    }

    public static List<Message> getMessagesFromConversationId(Integer id) {
        Conversation conversation = getConversationFromId(id);
        List<Integer> testMes = new ArrayList<Integer>();
        testMes.add(1);
        testMes.add(3);

        conversation.setMessages(testMes);

        if(conversation != null ) {
            List<Message> results = new ArrayList<Message>();
            List<Integer> messageId = conversation.getMessages();

                for (Message m : messages) {
                    Integer message_id = m.getMessage_id();
                    if (messageId.contains(message_id)) {
                        results.add(m);
                    }
                }

                return results;
        }
        return null;
    }

    public void addCount(Integer newCount){
        if(isBelowLimit(newCount)) {
            charCount.setCount(newCount + charCount.getCount());;
            charCount.setDate(new Date());
        }

    }

    public static boolean isBelowLimit(Integer newCount){
        if(newCount + charCount.getCount() <= charCount.getLimit()) {
            return true;
        }
        return false;
    }


    static class DailyCount {
        private Integer count;
        private Date date;
        private Integer limit = 100;

        public Integer getLimit() {
            return limit;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            if(count <= limit) {
                this.count = count;
            } else {
                this.count = limit;
            }
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }


        public Integer getTodayCount() {
//            Date now = new Date();
//            //Just get the time in day, not hours or minutes or seconds
//            long nowTime = now.getTime() - now.getTime() % 86400000;
//            long lastSet = date.getTime() - date.getTime() % 86400000;
//
//            if(nowTime != lastSet) {
//                count = 0;
//            }

            return 5;
        }




    }



}
