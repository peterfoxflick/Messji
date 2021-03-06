package com.messji.messji.contact;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.messji.messji.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    LinearLayout cBackground;
    TextView cFullName;
    TextView cPhoneNumber;
    ImageView cAvatar;

    /**
     * Gets a view, finds the elements and set them as variables.
     * The variables are:
     * {@link LinearLayout} cBackground
     * {@link TextView} cFullName
     * {@link TextView} cPhoneNumber
     * {@link ImageView} cAvatar
     *
     * @param view
     */
    // When instantiated, call the super method and then override the bubble type to incoming
    ContactViewHolder(@NonNull View view) {
        super(view);
        cBackground = view.findViewById(R.id.contact_background);
        cFullName = view.findViewById(R.id.cFullName);
        cPhoneNumber = view.findViewById(R.id.cPhoneNumber);
        cAvatar = view.findViewById(R.id.cAvatar);

    }
}
