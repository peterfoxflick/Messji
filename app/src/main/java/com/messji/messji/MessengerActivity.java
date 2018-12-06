package com.messji.messji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;

public class MessengerActivity extends AppCompatActivity implements Serializable {

    private EditText editText;
    private ItemAdapter messageAdapter;
    private RecyclerView messagesView;
    private int convId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        convId = intent.getIntExtra("convId", -1);

        Serializable convo = getIntent().getSerializableExtra("Conversation");
        Log.d("nCreate:", "Conversation is: " + convo);

        this.setTitle("User Name here");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        editText = (EditText) findViewById(R.id.editText);

        Database.loadDatabase(this);
        Database.loadCharCount(this);

        // if negative 1 go back to home

        //Use conv ID to populate messages here

        /*JSONArray conversations = new JSONArray(Database.getConversations());

        for (int i = 0; i < conversations.length(); i++) {
            try {
                if ((conversations.getInt(0) ) == convId) {  //convID = -1 right now"
                    //load up these messages since they are a match
                    Log.d("if statement", "it's a match!");
                    //Need to get all matching conversation messages to the view
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

       
       //messageAdapter = new ItemAdapter(Database.getmess)
       // messageAdapter = new ItemAdapter(Database.getMessages());
        messagesView = findViewById(R.id.messages_view);
        messagesView.setLayoutManager(new LinearLayoutManager(this));
        messagesView.setAdapter(messageAdapter);

        /*
            NOTE: The line below replaces the whole content of your UI with the ItemFragment() only
            This was causing the issue where you couldn't tap on the EditText (it didn't exist anymore)
            Instead, I included the RecyclerView in the activity layout and configure it above
        */
        //getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new ItemFragment()).commit();

    };

    public void sendMessage(View view) {
        Log.v("sendMessage","IN SEND MESSAGE FUNCTION");

        // if the clientID of the message sender is the same as our's it was sent by us
        boolean belongsToCurrentUser = true; //Just for now

        /*
            TODO: The user id needs to the user id
         */

        final Message message = new Message(editText.getText().toString(), 1);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(Database.isBelowLimit(message.getText().length()) ) {
                    Database.addMessage(message, convId);
                    Log.d("getMessages:", "Size is: " + Database.getMessages().size());

                    messageAdapter.notifyItemInserted(Database.getMessages().size() - 1);
                }

                // scroll the RecyclerView to the last added element
                messagesView.smoothScrollToPosition(Database.getMessages().size() - 1);
            }
        });

        // Clear the text field after sending the message
        editText.getText().clear();
    }

    public void onClose(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("draftText",editText.getText().toString());
        editor.putInt("convId", convId );
        editor.commit();
    }
}

