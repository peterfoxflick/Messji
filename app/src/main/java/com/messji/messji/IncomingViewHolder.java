package com.messji.messji;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * The view holder for incoming messages
 */
class IncomingViewHolder2 extends MessageViewHolder2 {

    // When instantiated, call the super method and then override the bubble type to incoming
    IncomingViewHolder2(@NonNull View view) {
        super(view);
        mBubbleType = BubbleType.INCOMING;
    }
}