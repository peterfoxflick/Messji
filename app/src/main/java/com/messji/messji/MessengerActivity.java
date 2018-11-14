package com.messji.messji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MessengerActivity extends AppCompatActivity {
    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private int convId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        convId = intent.getIntExtra("convId", -1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        editText = (EditText) findViewById(R.id.editText);

        // if negative 1 go back to home

        //Use conv ID to populate messages here

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if(prefs.getInt("lastConvId", -1) == convId){
            String draft = prefs.getString("draftText", "");
            editText.setText(draft);
        }

        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        };

    public void sendMessage(View view) {
        System.out.println("IN SEND MESSAGE FUNCTION");

        /*try {*/
        // if the clientID of the message sender is the same as our's it was sent by us
        boolean belongsToCurrentUser = true;//Just for now
        // if it was instead an object we could use a similar pattern to data parsing
        final Message message = new Message(editText.getText().toString(), 0, 0, belongsToCurrentUser);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
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

