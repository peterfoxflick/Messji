package com.messji.messji.ContactPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.messji.messji.Database;
import com.messji.messji.R;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Database.loadUsers(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_contacts , new ContactFragment()).commit();
    }
}
