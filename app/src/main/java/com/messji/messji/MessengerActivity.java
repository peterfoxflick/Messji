package com.messji.messji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.Random;

public class MessengerActivity extends AppCompatActivity {
    ;
    private EditText editText;
    // private Scaledrone scaledrone;
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

       /*     @Override
            public void onOpen() {
                System.out.println("IN ONOPEN METHOD");
                // Since the MainActivity itself already implement RoomListener we can pass it as a target
                scaledrone.subscribe(roomName, MessengerActivity.this);
            }

            @Override
            public void onOpenFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onClosed(String reason) {
                System.err.println(reason);
            }*/
        };
//
//    @Override
//    public void onOpen(Room room) {
//        System.out.println("Conneted to room");
//    }
//
//    @Override
//    public void onOpenFailure(Room room, Exception ex) {
//        System.err.println(ex);
//    }
//
//
    //@Override
    /*public void onMessage(Room room, final JsonNode json, final Member member) {
        System.out.print("IN ONMESSAGE METHOD@@@@@@@@@@@@@@@@@@@@@@");
        // To transform the raw JsonNode into a POJO we can use an ObjectMapper
        final ObjectMapper mapper = new ObjectMapper();
        try {
            // member.clientData is a MemberData object, let's parse it as such
            final MemberData data = mapper.treeToValue(member.getClientData(), MemberData.class);
            // if the clientID of the message sender is the same as our's it was sent by us
            boolean belongsToCurrentUser = member.getId().equals(scaledrone.getClientID());
            // since the message body is a simple string in our case we can use json.asText() to parse it as such
            // if it was instead an object we could use a similar pattern to data parsing
            final Message message = new Message(json.asText(), data, belongsToCurrentUser);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(message);
                    // scroll the ListView to the last added element
                    messagesView.setSelection(messagesView.getCount() - 1);
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/

    public void sendMessage(View view) {
        System.out.println("IN SENDMESSAGE FUNCTION");
        /*String message = editText.getText().toString();
        if (message.length() > 0) {
            scaledrone.publish("observable-room", message);
            editText.getText().clear();*/
    }
}

//   }
//
//
//class MemberData {
//    private String name;
//    private String color;
//
//    public MemberData(String name, String color) {
//        this.name = name;
//        this.color = color;
//    }
//
//    // Add an empty constructor so we can later parse JSON into MemberData using Jackson
//    public MemberData() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    @Override
//    public String toString() {
//        return "MemberData{" +
//                "name='" + name + '\'' +
//                ", color='" + color + '\'' +
//                '}';
//    }
//}


