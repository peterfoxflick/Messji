package com.messji.messji;

import java.io.Serializable;

class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int photo;

    public User(String firstName, String lastName, String phoneNumber, int photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    // Only store the data and use helper functions for UI convenience
    public String getFullName() {

        if (firstName.isEmpty() && lastName.isEmpty())
            return phoneNumber;
        
        return firstName + " " + lastName;
    }
}