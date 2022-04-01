package com.example.votum.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private int userID;
    private String email;
    private String password;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(int userID, String email, String password, String birthDate, String firstName, String lastName, String phoneNumber) {
        this.userID = userID;
        this.email = email;
        this.password = password;

        String[] birthdayArray = birthDate.split("-");
        int year = Integer.parseInt(birthdayArray[0])-1900;
        int month = Integer.parseInt(birthdayArray[1])-1;
        int day = Integer.parseInt(birthdayArray[2]);

        this.birthDate = new Date(year,month,day);

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(String email, String password, String birthDate, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;

        String[] birthdayArray = birthDate.split("-");
        int year = Integer.parseInt(birthdayArray[0])-1900;
        int month = Integer.parseInt(birthdayArray[1])-1;
        int day = Integer.parseInt(birthdayArray[2]);

        this.birthDate = new Date(year,month,day);

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(birthDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
