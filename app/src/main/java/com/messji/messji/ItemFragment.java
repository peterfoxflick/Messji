package com.messji.messji;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The main fragment in the application, which shows the RecyclerView
 */
public class ItemFragment extends Fragment {

    // Initialize the list of sample strings that will be used inside the adapter
    public static final List<String> sSampleStrings = Arrays.asList(
            "Hey, how are you doing?",
            "What's going on!?",
            "What time are you arriving?",
            "When is the show tonight?",
            "Want to grab tacos?",
            "Sorry, I'm super sick!",
            "What did you need from the store again?",
            "How about this weekend?"
    );

    // For when the view is first created
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Initialize a large list of messages by combining the sample strings and then shuffling
        List<String> mMessages = new ArrayList<>();
        for (int i = 0; i < 999; i++) {
            mMessages.addAll(sSampleStrings);
        }
        Collections.shuffle(mMessages);

        // Initialize the view for the fragment and set the RecyclerView layout manager and adapter
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setLayoutManager(new LinearLayoutManager(requireContext()));
            ((RecyclerView) view).setAdapter(new ItemAdapter(mMessages));
        }

        // Return the view back to the caller
        return view;
    }
}
