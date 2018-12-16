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
import java.util.List;

/**
 * The main fragment in the application, which shows the RecyclerView
 */
public class ItemFragment extends Fragment {

    // For when the view is first created
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        List<Message> mMessages = new ArrayList<>();
        mMessages = Database.getMessages();

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
