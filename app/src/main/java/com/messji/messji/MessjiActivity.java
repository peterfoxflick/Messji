package com.messji.messji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MessjiActivity extends AppCompatActivity {

    User myUser = new User("Douglas", "C. Hanson", "+18017457869", R.drawable.avitar);

    User users[] =
            {
                    new User("Henrique", "Tedeschi", "+18013477691", R.drawable.ic_users_1),
                    new User("Peter", "Flick", "+12087018132", R.drawable.ic_users_2),
                    new User("Thomas", "Rosales", "+17142618202", R.drawable.ic_users_3)
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get shared preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int lastConvId = prefs.getInt("conversationId", -1);
        if(lastConvId > -1) {
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

        adapter.addAll(users);
    }

    public void openMessage(View view) {
        Intent intent = new Intent(this, MessengerActivity.class);
        // this will not be User class, it will be a conversation
        intent.putExtra("myUser", myUser);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("myUser", myUser);
        startActivity(intent);
    }
}
