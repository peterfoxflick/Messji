package com.messji.messji;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Random;

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
    public void onBindViewHolder(@NonNull ConversationViewHolder conversationViewHolder, int position) {
        Log.d("On Bind View", "Youre in");

        // Get the message at the position in the list and set the text to the view holder
        //String title = mConversation.get(position).getTitle();
        System.out.println("The MessageID name is: " + mConversation.get(position).id);

        conversationViewHolder.mName.setText("Julius Caesar");
        conversationViewHolder.mLastMessage.setText(mConversation.get(position).title);
        conversationViewHolder.mTimeStamp.setText("4:53 PM 11/28/2018");
        conversationViewHolder.mAvatar.setImageResource(R.drawable.avitar);

        Log.d("On Bind View", "YOu set the text to Howdy Partner");
    }

    // For getting the number of items in the adapter
    @Override
    public int getItemCount() {
        return mConversation.size();
    }
}
