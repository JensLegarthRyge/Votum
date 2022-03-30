package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRepository {
    private static Statement stmt;
    private static Connection con;


    public void connectTo(){
        try {
            //Get a connection to the database for a user named root with password admin
            con = DriverManager.getConnection("jdbc:mysql://votum.mysql.database.azure.com:3306/votum", "rootuser@votum", "JJMdat21a");

            //Display the URL and connection information
            System.out.println("URL: " + "jdbc:mysql://votum.mysql.database.azure.com:3306/votum");
            System.out.println("Connection: " + con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void disconnectFrom(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
