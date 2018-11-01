package com.messji.messji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        User user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_contacts, parent, false);
        }

        //        Lookup view for data population
        //        ImageView imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
        //        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        //        TextView txtLastMessage = (TextView) convertView.findViewById(R.id.txtLastMessage);
        //        TextView txtDateLastMessage = (TextView) convertView.findViewById(R.id.txtDateLastMessage);

        //        imgAvatar.setImageResource(user.avatar);
        //        txtName.setText(user.name);
        //        txtLastMessage.setText(user.lastMessage.getText());
        //        txtDateLastMessage.setText(new SimpleDateFormat("HH:mm MM/dd/yy").format(user.lastMessage.getDate()));

        ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtLastMessage = (TextView) convertView.findViewById(R.id.txtLastMessage);
        TextView txtDateLastMessage = (TextView) convertView.findViewById(R.id.txtDateLastMessage);

        // Populate the data into the template view using the data object
        imgPhoto.setImageResource(user.getPhoto());
        txtName.setText(user.getFullName());
        txtLastMessage.setText("Last message goes here");
        txtDateLastMessage.setText(new SimpleDateFormat("HH:mm MM/dd/yy").format(new Date()));

        // Return the completed view to render on screen
        return convertView;
    }
}
