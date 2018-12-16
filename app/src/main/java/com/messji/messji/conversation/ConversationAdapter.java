package com.messji.messji.conversation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.messji.messji.Database;
import com.messji.messji.MessengerActivity;
import com.messji.messji.R;
import com.messji.messji.contact.Contact;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationViewHolder> {
    private List<Conversation> mConversation;

    // Store the passed in messages so it can be used in the methods below
    ConversationAdapter(List<Conversation> conversations) {
        mConversation = conversations;
    }

    // For when the view holder is initially created
    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Inflate the item layout when the view holder is created
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.conversation_item, viewGroup, false);
        return new ConversationViewHolder(view);
    }

    // For when the view holder is bound to a new position
    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder conversationViewHolder, final int position) {
        Log.d("On Bind View", "Youre in");
        Conversation conversation = mConversation.get(position);
        List<Contact> senders = Database.getContactsFromConversation(conversation);

        // Get the message at the position in the list and set the text to the view holder
        Log.i("Conversation Build:", "Conversation title: " + conversation.getTitle() + " And id is: " + conversation.getId());

        String name = "";
        for (Contact c : senders) {
            name += c.getFullName() + " ";
        }


        conversationViewHolder.mName.setText(name);
        conversationViewHolder.mLastMessage.setText(conversation.getTitle());
        conversationViewHolder.mTimeStamp.setText("4:53 PM 11/28/2018");


        int hexPhoto = R.drawable.ic_users_1;

        if (senders.get(0).getPhoto() == 2)
            hexPhoto = R.drawable.ic_users_2;

        if (senders.get(0).getPhoto() == 3)
            hexPhoto = R.drawable.ic_users_3;


        conversationViewHolder.mAvatar.setImageResource(hexPhoto);


        conversationViewHolder.mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(v.getContext().toString(), "Getting ready to open an existent conversation. Index: " + position);

                Intent intent = new Intent(v.getContext(), MessengerActivity.class);

                Conversation conversation = mConversation.get(position);
                intent.putExtra("Conversation", new Gson().toJson(conversation, Conversation.class));

                v.getContext().startActivity(intent);

                Log.i(v.getContext().toString(), "Activity opened. Intent: " + intent.toString());
            }
        });

        Log.d("On Bind View", "YOu set the text to Howdy Partner");
    }

    // For getting the number of items in the adapter
    @Override
    public int getItemCount() {
        return mConversation.size();
    }
}
