package com.messji.messji.ContactPackage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.messji.messji.ConversationPackage.Conversation;
import com.messji.messji.MessengerActivity;
import com.messji.messji.R;

import java.io.Serializable;
import java.util.List;

/**
 * @author Henrique Tedeschi
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> implements Serializable {
    private List<Contact> mContact;

    // Store the passed in messages so it can be used in the methods below
    ContactAdapter(List<Contact> contacts) {
        mContact = contacts;
    }

    /**
     * Will create the ViewHolder, in this case a contact_item found in R.layout.*
     * @param viewGroup
     * @param viewType
     * @return Returns the view, in this case a contact_item found in R.layout.*
     */
    // For when the view holder is initially created
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Inflate the item layout when the view holder is created
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_item, viewGroup, false);
        return new ContactViewHolder(view);
    }

    /**
     * Everytime an item in the Recycler View is created, this will get the current data from the database and fill the data in the layout
     * @param contactViewHolder
     * @param position
     */
    // For when the view holder is bound to a new position
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, final int position) {
        contactViewHolder.cFullName.setText(mContact.get(position).getFullName());
        contactViewHolder.cPhoneNumber.setText(mContact.get(position).getPhoneNumber());

        int hexPhoto = R.drawable.ic_users_1;

        if (mContact.get(position).getPhoto() == 2)
            hexPhoto = R.drawable.ic_users_2;

        if (mContact.get(position).getPhoto() == 3)
            hexPhoto = R.drawable.ic_users_3;

        contactViewHolder.cAvatar.setImageResource(hexPhoto);

        contactViewHolder.cBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(v.getContext().toString(), "Getting ready to open a new conversation. Index: " + position);

                Intent intent = new Intent(v.getContext(), MessengerActivity.class);

                Contact contact = mContact.get(position);
                intent.putExtra("Contact", new Gson().toJson(contact, Contact.class));

                Conversation conversation = new Conversation();
                intent.putExtra("Conversation", new Gson().toJson(conversation, Conversation.class));

                v.getContext().startActivity(intent);

                Log.i(v.getContext().toString(), "Activity opened. Intent: " + intent.toString());
            }
        });
    }

    /**
     * @return Returns the number of items (Contacts) in the adapter
     */
    // For getting the number of items in the adapter
    @Override
    public int getItemCount() {
        return mContact.size();
    }
}
