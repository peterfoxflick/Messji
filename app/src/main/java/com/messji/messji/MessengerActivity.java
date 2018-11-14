package com.messji.messji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MessengerActivity extends AppCompatActivity {
    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        editText = (EditText) findViewById(R.id.editText);

        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        };

    public void sendMessage(View view) {
        System.out.println("IN SEND MESSAGE FUNCTION");

        /*try {*/
        // if the clientID of the message sender is the same as our's it was sent by us
        boolean belongsToCurrentUser = true;//Just for now
        // if it was instead an object we could use a similar pattern to data parsing
        final Message message = new Message(editText.getText().toString(), 0, 0, belongsToCurrentUser);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
            }
        });

        // Clear the text field after sending the message
        editText.getText().clear();
    }

            /*catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
}

