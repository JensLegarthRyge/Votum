
package com.example.votum.Repositories;

import java.sql.*;

public class DatabaseConnectionManager {

    public Connection getConnectionToDatabase(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://votum.mysql.database.azure.com:3306/votum", "rootuser@votum", "JJMdat21a");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
