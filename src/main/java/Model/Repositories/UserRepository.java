package Model.Repositories;

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

                String birthday = rs.getString("birth_date");
                String[] birthdayArray = birthday.split("-");
                int year = Integer.parseInt(birthdayArray[0])-1900;
                int month = Integer.parseInt(birthdayArray[1])-1;
                int day = Integer.parseInt(birthdayArray[2]);
                Date birthDate = new Date(year,month,day);


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
}
