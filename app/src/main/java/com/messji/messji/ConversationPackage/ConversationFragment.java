package com.messji.messji.ConversationPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.messji.messji.ContactPackage.Contact;
import com.messji.messji.Database;
import com.messji.messji.R;

import java.util.ArrayList;
import java.util.List;

public class ConversationFragment extends Fragment {
    // For when the view is first created
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Initialize a large list of messages by combining the sample strings and then shuffling
        List<Conversation> mConversation = new ArrayList<>();
        mConversation = Database.getConversations();

        // Initialize the view for the fragment and set the RecyclerView layout manager and adapter
        View view = inflater.inflate(R.layout.list_conversations, container, false);
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setLayoutManager(new LinearLayoutManager(requireContext()));
            ((RecyclerView) view).setAdapter(new ConversationAdapter(mConversation));
        }

        // Return the view back to the caller
        return view;
    }
}

