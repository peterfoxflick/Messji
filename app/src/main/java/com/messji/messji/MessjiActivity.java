package com.messji.messji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MessjiActivity extends AppCompatActivity {

    User myUser = new User("Douglas", "C. Hanson", "+18017457869", R.drawable.avitar);
    User[] contacts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getContacts();

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
        ArrayList<User> arrayOfUsers = new ArrayList<User>();

        // Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listContacts);
        listView.setAdapter(adapter);

        adapter.addAll(contacts);

        final Intent intent = new Intent(this, MessengerActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                User userClicked =  (User) adapter.getItemAtPosition(position);

                intent.putExtra("User", userClicked);
                startActivity(intent);
            }
        });
    }

    private void getContacts() {
        String contactsJson = null;
        try {
            InputStream is = getAssets().open("contacts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            contactsJson = new String(buffer, "UTF-8");

            contacts = new Gson().fromJson(contactsJson, User[].class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void openSettings(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("myUser", myUser);
        startActivity(intent);
    }
}
