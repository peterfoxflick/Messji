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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.io.Serializable;
import java.util.Random;

public class MessengerActivity extends AppCompatActivity implements Serializable {
    private EditText editText;
    // private Scaledrone scaledrone;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private int convId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        convId = intent.getIntExtra("convId", -1);

        Serializable userExtra = intent.getSerializableExtra("User");
        User user = (User) new Gson().fromJson(userExtra.toString(), User.class);


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
    }

    public void onClose(){

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("draftText",editText.getText().toString());
        editor.putInt("convId", convId );
        editor.commit();
    }




    public void sendMessage(View view) {
        System.out.println("IN SENDMESSAGE FUNCTION");

    }
}
