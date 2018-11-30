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

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private List<User> mContact;

    // Store the passed in messages so it can be used in the methods below
    ContactAdapter(List<User> contacts) {
        mContact = contacts;
    }

    // For when the view holder is initially created
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Inflate the item layout when the view holder is created
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_item, viewGroup, false);
        return new ContactViewHolder(view);
    }

    // For when the view holder is bound to a new position
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int position) {
        contactViewHolder.cFullName.setText(mContact.get(position).getFullName());
        contactViewHolder.cPhoneNumber.setText(mContact.get(position).getPhoneNumber());

        int hexPhoto = R.drawable.ic_users_1;

        if (mContact.get(position).getPhoto() == 2)
            hexPhoto = R.drawable.ic_users_2;

        if (mContact.get(position).getPhoto() == 3)
            hexPhoto = R.drawable.ic_users_3;

        contactViewHolder.cAvatar.setImageResource(hexPhoto);
    }

    // For getting the number of items in the adapter
    @Override
    public int getItemCount() {
        return mContact.size();
    }
}
