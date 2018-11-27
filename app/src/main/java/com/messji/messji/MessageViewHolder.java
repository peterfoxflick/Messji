package com.messji.messji;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * The base class for the message view holders
 */
class MessageViewHolder2 extends RecyclerView.ViewHolder {

    // Declare the UI elements for the view holder
    LinearLayout mBubbleBackground;
    TextView mBubbleContent;
    BubbleType mBubbleType;

    // When instantiated, keep a reference to the layout IDs and set the bubble type as unknown
    MessageViewHolder2(@NonNull View view) {
        super(view);
        mBubbleBackground = view.findViewById(R.id.bubble_background);
        mBubbleContent = view.findViewById(R.id.bubble_content);
        mBubbleType = BubbleType.UNKNOWN;
    }
}
