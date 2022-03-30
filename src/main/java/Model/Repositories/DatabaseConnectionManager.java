
package Model.Repositories;

import java.sql.*;

public class DatabaseConnectionManager {
    private Connection con;

    public Connection getConnectionToDatabase(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://votum.mysql.database.azure.com:3306/votum", "rootuser@votum", "JJMdat21a");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void connectTo(){
        try {
            //Get a connection to the database for a user named root with password admin
            con = DriverManager.getConnection("jdbc:mysql://votum.mysql.database.azure.com:3306/votum", "rootuser@votum", "JJMdat21a");
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
