package com.messji.messji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class MessengerActivity extends AppCompatActivity implements Serializable {

    private EditText editText;
    private ItemAdapter messageAdapter;
    private RecyclerView messagesView;
    private int convId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        convId = intent.getIntExtra("convId", -1);

       // Serializable userExtra = intent.getSerializableExtra("User");
       // User user = (User) new Gson().fromJson(userExtra.toString(), User.class);
        this.setTitle("User Name here");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        editText = (EditText) findViewById(R.id.editText);

        Database.loadDatabase(this);
        Database db = new Database();
        db.loadCharCount(this);


        // if negative 1 go back to home

        //Use conv ID to populate messages here
        //Database.loadConversations(this); TODO - this is causing a crash
        //JSONArray conversations = new JSONArray(Database.getConversations());
        /*for (int i = 0; i < conversations.length(); i++) {
            try {
                if ((conversations.getInt(0)) == convId) {   //Didn't get this to work quite yet, id is still coming in as "-1"
                    //load up these messages since they are a match
                    Log.d("if statement", "it's a mtach!");
                    //Need to get all matching conversation messages to the view
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

    //    if(prefs.getInt("lastConvId", -1) == convId){
    //        String draft = prefs.getString("draftText", "");
    //        editText.setText(draft);
    //    }




       // List<Message> msgs = new ArrayList<Message>();

        messageAdapter = new ItemAdapter(Database.getMessages());
        messagesView = findViewById(R.id.messages_view);
        messagesView.setLayoutManager(new LinearLayoutManager(this));
        messagesView.setAdapter(messageAdapter);

        /*
            NOTE: The line below replaces the whole content of your UI with the ItemFragment() only
            This was causing the issue where you couldn't tap on the EditText (it didn't exist anymore)
            Instead, I included the RecyclerView in the activity layout and configure it above
        */
        //getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new ItemFragment()).commit();

    };

    public void sendMessage(View view) {
        Log.v("sendMessage","IN SEND MESSAGE FUNCTION");

        Database.loadDatabase(this);
        Database db = new Database();

        /*try {*/
        // if the clientID of the message sender is the same as our's it was sent by us
        boolean belongsToCurrentUser = true;//Just for now

        // if it was instead an object we could use a similar pattern to data parsing

        /*
            TODO: The user id needs to the user id
         */

        final Message message = new Message(editText.getText().toString(), 1);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /*
                    TODO: You won't be able to directly add messages to a RecyclerView like you can a ListView
                    After adding your message to the database, you let the adapter know to go fetch the new data
                    Since you're adding a new message, I recommend looking at the documentation for notifyItemInserted()
                 */

                // messageAdapter.add(message);
                // scroll the ListView to the last added element
                // messagesView.setSelection(messagesView.getCount() - 1);
                Database db = new Database();
                if(db.isBelowLimit(message.getText().length()) ) {
                    db.addMessage(message, convId);
                }
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

