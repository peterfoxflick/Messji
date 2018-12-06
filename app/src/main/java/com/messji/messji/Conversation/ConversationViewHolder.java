package com.messji.messji.Conversation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.messji.messji.R;

public class ConversationViewHolder extends RecyclerView.ViewHolder {
    LinearLayout mBackground;
    TextView mName;
    TextView mLastMessage;
    TextView mTimeStamp;
    ImageView mAvatar;

    // When instantiated, call the super method and then override the bubble type to incoming
    ConversationViewHolder(@NonNull View view) {
        super(view);
        mBackground = view.findViewById(R.id.conversation_background);
        mName = view.findViewById(R.id.mName);
        mLastMessage = view.findViewById(R.id.mLastMessage);
        mTimeStamp = view.findViewById(R.id.mTimeStamp);
        mAvatar = view.findViewById(R.id.mAvatar);

    }
}
