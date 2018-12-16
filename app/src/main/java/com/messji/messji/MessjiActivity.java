package com.messji.messji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.messji.messji.ContactPackage.Contact;
import com.messji.messji.ContactPackage.ContactsActivity;
import com.messji.messji.ConversationPackage.Conversation;
import com.messji.messji.ConversationPackage.ConversationFragment;

public class MessjiActivity extends AppCompatActivity {

    Contact myContact = new Contact("Douglas", "C. Hanson", "+18017457869", R.drawable.avitar);
    Contact[] contacts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        //Get shared preferences --This will work one day....
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        int lastConvId = prefs.getInt("conversationId", -1);
//        if (lastConvId > -1) {
//            //if in conversation go there
//
//            Intent intent = new Intent(this, MessengerActivity.class);
//            // this will not be Contact class, it will be a conversation
//            intent.putExtra("myContact", myContact);
//            intent.putExtra("lastConvId", lastConvId);
//            startActivity(intent);
//        }

        //else

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messji);

        // Construct the data source
        Database.loadDatabase(this);
        Conversation conversation = (Conversation) Database.getConversations().get(0);
        Log.i("Loading Mesgi", "Conversation title: " + conversation.getTitle());

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
                profileIntent.putExtra("myContact", myContact);
                startActivity(profileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
