package Model.Repositories;

import Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class UserRepository {
    private final DatabaseConnectionManager dcm;

    public UserRepository() {
        dcm = new DatabaseConnectionManager();
    }

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


}
