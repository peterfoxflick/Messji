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

/**
 * The adapter for the RecyclerView, which shows messages on the screen
 */
public class ItemAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    // Declare the list of messages that the adapter will pull from
    private static List<Message> mMessages;

    // Store the passed in messages so it can be used in the methods below
    ItemAdapter(List<Message> messages) {
        mMessages = messages;
    }

    // For when the view holder is initially created
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.v("MessageViewHolder:", "In MessageViewHoler");

        // Inflate the item layout when the view holder is created
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_item, viewGroup, false);

        // Depending on the view type, either return the incoming or outgoing view holder
        if (viewType == 0) {
            return new IncomingViewHolder(view);
        } else {
            return new OutgoingViewHolder(view);
        }
    }

    // For when the view holder is bound to a new position
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int position) {
        Log.d("On Message View", "Youre in");
        // Get the message at the position in the list and set the text to the view holder
        mMessages = Database.getMessages();  // -- This should update the databse so the newest message will show, but it crashes after the first message
        String message = mMessages.get(position).getText();
        Log.v("onBindViewHolder:", "message is: " + message);
        messageViewHolder.mBubbleContent.setText(
                new StringBuilder().append("(").append(position).append(") ").append(message)
        );

        // Show a different visual treatment for incoming and outgoing messages
        if (messageViewHolder instanceof IncomingViewHolder) {
            messageViewHolder.mBubbleBackground.setGravity(Gravity.START);
            messageViewHolder.mBubbleContent.setBackgroundResource(R.drawable.bg_incoming);
        } else {
            messageViewHolder.mBubbleBackground.setGravity(Gravity.END);
            messageViewHolder.mBubbleContent.setBackgroundResource(R.drawable.bg_outgoing);
        }
    }

    // For checking what type of view should be returned based on the adapter position
    @Override
    public int getItemViewType(int position) {
        return new Random().nextInt(2); //TODO: Assign proper mesage to correct user
    }   //

    // For getting the number of items in the adapter
    @Override
    public int getItemCount() {
        return mMessages.size();
    }
}

