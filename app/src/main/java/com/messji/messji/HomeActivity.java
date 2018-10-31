package com.messji.messji;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    User contacts[] =
            {
                    new User(R.drawable.ic_users_1, "Henrique Tedeschi", new Message("Hey, what's up?", new Date())),
                    new User(R.drawable.ic_users_2, "Peter Flick", new Message("No, I am eating rn..", new Date())),
                    new User(R.drawable.ic_users_3, "Thomas Rosales", new Message("Hahaha, totally!", new Date())),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();

        // Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listContacts);
        listView.setAdapter(adapter);

        adapter.addAll(contacts);

    }

}
