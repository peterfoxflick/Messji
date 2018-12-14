package com.messji.messji.ContactPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.messji.messji.Database;
import com.messji.messji.R;

/**
 * @author Henrique Tedeschi
 */
public class ContactsActivity extends AppCompatActivity {

    /**
     * On the create, will set the recycler view ready. It will fill the layout linear_contacts found in R.layout.*
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Database.loadUsers(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_contacts , new ContactFragment()).commit();
    }
}
