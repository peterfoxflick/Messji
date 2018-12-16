package com.messji.messji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.messji.messji.contact.Contact;
import com.messji.messji.contact.ContactsActivity;
import com.messji.messji.conversation.Conversation;
import com.messji.messji.conversation.ConversationFragment;

/**
 * @author Henrique Tedeschi
 */
public class MessjiActivity extends AppCompatActivity {

    Contact myContact = new Contact("Douglas", "C. Hanson", "+18017457869", R.drawable.avitar);
    Contact[] contacts = null;

    /**
     * When the main activity is created, fill the recycler view with the conversations
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messji);

        // Construct the data source
        Database.loadDatabase(this);
        Conversation conversation = (Conversation) Database.getConversations().get(0);
        Log.i("Loading Messji", "Conversation title: " + conversation.getTitle());

        getSupportFragmentManager().beginTransaction().replace(R.id.heregoesthething, new ConversationFragment()).commit();
    }

    /**
     * Will create the options on the toolbar of the activity
     *
     * @param menu
     * @return Returns {@link true}
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    /**
     * Will choose what to do according to the button clicked on the toolbar of the activity.
     * If clicked on new conversation, start the contacts activity
     * If clicked on profile, start the profile activity
     * If none of those, do nothing
     *
     * @param item
     * @return Returns {@link true} unless clicked on an invalid option, then returns the item selected
     */
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
