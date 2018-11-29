package com.messji.messji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MessjiActivity extends AppCompatActivity {

    User myUser = new User("Douglas", "C. Hanson", "+18017457869", R.drawable.avitar);
    User[] contacts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Load in from database
        Database.loadConversations(this);

        //Get shared preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int lastConvId = prefs.getInt("conversationId", -1);
        if (lastConvId > -1) {
            //if in conversation go there

            Intent intent = new Intent(this, MessengerActivity.class);
            // this will not be User class, it will be a conversation
            intent.putExtra("myUser", myUser);
            intent.putExtra("lastConvId", lastConvId);
            startActivity(intent);
        }

        //else


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messji);

        // Construct the data source
        Database.loadConversations(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.heregoesthething , new ConversationFragment()).commit();
    }

    
    public void openSettings(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("myUser", myUser);
        startActivity(intent);
    }

    public void openMessenger(View view){
        Intent intent = new Intent(this, MessengerActivity.class);
        intent.putExtra("User", myUser);
        startActivity(intent);
    }
}
