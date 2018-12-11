package com.messji.messji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.messji.messji.ConversationPackage.ConversationFragment;

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

        getSupportFragmentManager().beginTransaction().replace(R.id.heregoesthething, new ConversationFragment()).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_converstaion:
                Intent contactsIntent = new Intent(this, ContactsActivity.class);
                startActivity(contactsIntent);
                return true;
            case R.id.action_see_profile:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                profileIntent.putExtra("myUser", myUser);
                startActivity(profileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
