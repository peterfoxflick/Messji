package com.messji.messji.contact;

import java.io.Serializable;

/**
 * @author Henrique Tedeschi
 */
public class Contact implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int photo;


    /**
     * Contact constructor
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param photo
     */
    public Contact(String firstName, String lastName, String phoneNumber, int photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }

    /**
     * @return Returns the Contact id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Contact id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Returns the Contact First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the Contact First Name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Returns the Contact Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the Contact Last Name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return Returns the Contact Phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the Contact Phone Number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return Returns the Contact Photo
     * The photo will return an integer corresponding to a Resource found in R.drawable.*
     */
    public int getPhoto() {
        return photo;
    }

    /**
     * Sets the Contact Photo
     * It should set an integer corresponding to a Resource found in R.drawable.*
     * @param photo
     */
    public void setPhoto(int photo) {
        this.photo = photo;
    }

    /**
     * @return Returns the Contact Full Name (First Name + " " Last Name)
     */
    // Only store the data and use helper functions for UI convenience
    public String getFullName() {

        if (firstName.isEmpty() && lastName.isEmpty())
            return phoneNumber;
        
        return firstName + " " + lastName;
    }
}