package Model;

import java.util.Date;

public class User {
    private int userID;
    private String email;
    private String password;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(int userID, String email, String password, Date birthDate, String firstName, String lastName, String phoneNumber) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
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
}
