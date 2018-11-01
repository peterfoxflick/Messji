package com.messji.messji;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MessjiActivity extends AppCompatActivity {

    User contacts[] =
            {
                    new User("Henrique", "Tedeschi", "+18013477691", R.drawable.ic_users_1),
                    new User("Peter", "Flick", "+12087018132", R.drawable.ic_users_2),
                    new User("Thomas", "Rosales", "+17142618202", R.drawable.ic_users_3)
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    public void openMessage(View view) {
        Intent intent = new Intent(this, MessengerActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
