package com.messji.messji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.messji.messji.conversation.Conversation;
import com.messji.messji.deals.DailyDeal;

import java.io.Serializable;
import java.util.List;

public class MessengerActivity extends AppCompatActivity implements Serializable {

    private EditText editText;
    private ItemAdapter messageAdapter;
    private RecyclerView messagesView;
    private Integer convId;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        // convId = intent.getIntExtra("convId", -1);

        Serializable convExtra = getIntent().getSerializableExtra("Conversation");
        Log.d("onCreate:", "Conversation is: " + convExtra);

        Conversation conversation = new Gson().fromJson(convExtra.toString(), Conversation.class);
        convId = conversation.getId();

        //this.setTitle("Conversation Title: " + conversation.getTitle());
        this.setTitle("You have " + Database.loadCharCount(this).toString() + " characters left");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        editText = (EditText) findViewById(R.id.editText);

        Database.loadDatabase(this);

        //Use convID to populate messages here
        messages = Database.getMessagesFromConversationId(convId);

        messageAdapter = new ItemAdapter(messages);
        messagesView = findViewById(R.id.messages_view);
        messagesView.setLayoutManager(new LinearLayoutManager(this));
        messagesView.setAdapter(messageAdapter);
    }

    public void sendMessage(View view) {
        Log.v("sendMessage", "In Beginning of Send Message");

        boolean belongsToCurrentUser = true;    //We're the one's sending the message

        final Message message = new Message(editText.getText().toString(), 1);

        final DailyDeal dailyDeal = DailyDeal.getTodaysDeal(message);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dailyDeal.canSend()) {
                    Log.d("getMessages:", "Size is: " + Database.getMessages().size());
                    messages.add(message);
                    messageAdapter.notifyItemInserted(messages.size() - 1);
                    dailyDeal.outgoingMessage(convId);
                }

                // scroll the RecyclerView to the last added element
                messagesView.smoothScrollToPosition(Database.getMessages().size() - 1);
            }
        });

        this.setTitle("You have " + Database.getCharCount().toString() + " characters left");

        // Clear the text field after sending the message
        editText.getText().clear();
    }

    public void onPause() {
        super.onPause();
        Database db = new Database();
        db.save(this);
    }

}

