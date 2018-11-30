package com.messji.messji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Database.loadUsers(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_contacts , new ContactFragment()).commit();
    }
}
