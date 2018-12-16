package com.messji.messji.contact;

import com.messji.messji.R;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTests {

    String _firstName = "John";
    String _lastName = "Smith";
    String _phone = "8013477691";
    int _picture = R.drawable.ic_users_1;

    Contact c = new Contact(_firstName, _lastName, _phone, _picture);

    @Test
    public void getAndSetId() {
        c.setId(99);
        assertNotNull(c.getId());
        assertEquals(99, c.getId());
    }

    @Test
    public void getFirstName() {
        assertEquals(_firstName, c.getFirstName());
    }

    @Test
    public void setFirstName() {
        String firstName = "Brady";
        c.setFirstName(firstName);
        assertEquals(firstName, c.getFirstName());
    }

    @Test
    public void getLastName() {
        assertEquals(_lastName, c.getLastName());
    }

    @Test
    public void setLastName() {
        String lastName = "Christian";
        c.setLastName(lastName);
        assertEquals(lastName, c.getLastName());
    }

    @Test
    public void getPhoneNumber() {
        assertEquals(_phone, c.getPhoneNumber());
    }

    @Test
    public void setPhoneNumber() {
        String phone = "9787900290";
        c.setPhoneNumber(phone);
        assertEquals(phone, c.getPhoneNumber());
    }

    @Test
    public void getPhoto() {
        assertEquals(_picture, c.getPhoto());
    }

    @Test
    public void setPhoto() {
        int photo = R.drawable.ic_users_2;
        c.setPhoto(photo);
        assertEquals(photo, c.getPhoto());
    }

    @Test
    public void getFullName() {
        assertEquals(_firstName + " " + _lastName, c.getFullName());
    }
}