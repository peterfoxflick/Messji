package com.messji.messji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class MessengerActivity extends AppCompatActivity implements Serializable {

    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private int convId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        convId = intent.getIntExtra("convId", -1);

       // Serializable userExtra = intent.getSerializableExtra("User");
       // User user = (User) new Gson().fromJson(userExtra.toString(), User.class);
        this.setTitle("User Name here");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        editText = (EditText) findViewById(R.id.editText);

        // if negative 1 go back to home

        //Use conv ID to populate messages here
        Database.loadConversations(this);
        JSONArray conversations = new JSONArray(Database.getConversations());
        for (int i = 0; i < conversations.length(); i++) {
            try {
                if ((conversations.getInt(0)) == convId) {   //Didn't get this to work quite yet, id is still coming in as "-1"
                    //load up these messages since they are a match
                    Log.d("if statement", "it's a mtach!");
                    //Need to get all matching conversation messages to the view
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

    //    if(prefs.getInt("lastConvId", -1) == convId){
    //        String draft = prefs.getString("draftText", "");
    //        editText.setText(draft);
    //    }


        // Construct the data source
        Database.loadMessages(this);


       // List<Message> msgs = new ArrayList<Message>();

        messageAdapter = new MessageAdapter(this, Database.getMessages() );
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);




    };

    public void sendMessage(View view) {
        Log.v("sendMessage","IN SEND MESSAGE FUNCTION");

        /*try {*/
        // if the clientID of the message sender is the same as our's it was sent by us
        boolean belongsToCurrentUser = true;//Just for now

        // if it was instead an object we could use a similar pattern to data parsing
        final Message message = new Message(editText.getText().toString(), convId, 0, belongsToCurrentUser);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
                Database.addMessage(message);     //add the message to the database - this is very primitive right now
            }
        });

        // Clear the text field after sending the message
        editText.getText().clear();
    }

            /*catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

    public void onClose(){

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("draftText",editText.getText().toString());
        editor.putInt("convId", convId );
        editor.commit();
    }
}

