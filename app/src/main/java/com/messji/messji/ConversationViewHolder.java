package com.messji.messji;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversationViewHolder extends RecyclerView.ViewHolder {
    ImageView imgPhoto;
    TextView txtName;
    TextView txtLastMessage;
    TextView txtDateLastMessage;


    public ConversationViewHolder(@NonNull View itemView) {
        super(itemView);
        imgPhoto = itemView.findViewById(R.id.imgPhoto);
        txtName = itemView.findViewById(R.id.txtName);
        txtLastMessage = itemView.findViewById(R.id.txtLastMessage);
        txtDateLastMessage = itemView.findViewById(R.id.txtDateLastMessage);
    }
}
