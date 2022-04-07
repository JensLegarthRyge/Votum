package com.example.votum.Repositories;

import com.example.votum.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//I denne klasse har vi samlet de metoder som henter eller giver data videre der omhandler enkelte brugere.
public class UserRepository {
    private final DatabaseConnectionManager dcm;

    //Constructor til klassen som bare laver en connection til databasen.
    public UserRepository() {
        dcm = new DatabaseConnectionManager();
    }

    //Metode som tager imod ingen parameter og returnere en liste af alle users som den henter fra databasen.
    public ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                int userID = rs.getInt("user_id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthDate = rs.getString("birth_date");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phoneNumber = rs.getString("phone_number");

                allUsers.add(new User(userID,email,password,birthDate,firstName,lastName,phoneNumber));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } return allUsers;
    }

    //Metode som tilføjer en user til en database. Den tager imod en User som parameter og returnere ingenting.
    public void addUserToDatabase(User user){
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement();

            String email = user.getEmail().toLowerCase();
            String password = user.getPassword();
            String birthdate = user.getBirthDate();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String phoneNumber = user.getPhoneNumber();

            String query = "INSERT INTO `votum`.`users` (`email`, `password`, `birth_date`, `first_name`, `last_name`, `phone_number`)"
                    + "VALUES ('"+email+"', BINARY '"+password+"', '"+birthdate+"', '"+firstName+"', '"+lastName+"', '"+phoneNumber+"');";

            stmt.executeUpdate(query);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodes som tjekker om login er valid. Dere tjekkes gennem databasen sådan så passwords forbliver inde i mySQL og ikke ryger rundt omkring.
    public boolean isLoginValid(String email, String password){
        boolean isLoginValid = false;
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(
                    "SELECT " +
                        "CASE " +
                        "WHEN u.email = '"+email+"' and u.password = BINARY '"+password+"' THEN 'True'" +
                        "ELSE 'False'" +
                        "END AS is_login_valid " +
                        "FROM votum.users as u " +
                        "WHERE u.email = '"+email+"'");

            rs.next();
            isLoginValid = rs.getBoolean("is_login_valid");

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } return isLoginValid;

    }

    //Metode som bruges når der oprettes en bruger. Her tjekkes der om emailen allerede findes i databasen da der ikke kan være flere brugere med samme mailadressen
    //Der tages en String i parameterne og der returneres en boolean.
    public boolean isMailTaken(String email){
        boolean isMailTaken = false;
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(
                    "SELECT " +
                        "CASE " +
                        "WHEN u.email = '"+email+"' THEN 'True'" +
                        "ELSE 'False'" +
                        "END AS is_email_taken " +
                        "FROM votum.users as u " +
                        "WHERE u.email = '"+email+"'");

            rs.next();
            isMailTaken = rs.getBoolean("is_email_taken");

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } return isMailTaken;
    }


    //I denne metode tages der imod en String (email) og så returneres der en Use-object som bliver lavet
    //på baggrund af alle de oplysnigner som hører til denne email.
    public User getUserFromEmail(String userEmail){
        User userToReturn = null;
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM users as u WHERE u.email = '"+userEmail+"'");

            rs.next();
            int userID = rs.getInt("user_id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String birthDate = rs.getString("birth_date");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String phoneNumber = rs.getString("phone_number");

            userToReturn = new User(userID,email,password,birthDate,firstName,lastName,phoneNumber);

            con.close();

        } catch (SQLException e) {
            System.out.println("Mail is taken");
        } return userToReturn;
    }
}
