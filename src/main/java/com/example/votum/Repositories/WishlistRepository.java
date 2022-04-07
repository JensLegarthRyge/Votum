package com.example.votum.Repositories;

import com.example.votum.model.Wishlist;

import java.sql.*;
import java.util.ArrayList;

//I denne klasse har vi samlet de metoder som henter eller giver data videre der omhandler ønskelisterne.
public class WishlistRepository {
    private final DatabaseConnectionManager dcm;

    //Constructor til klassen som bare laver en connection til databasen.
    public WishlistRepository() {
        dcm = new DatabaseConnectionManager();
    }

    //Metode som tager imod en integer og returnere en liste med de ønskelister som brugeren med dette ID tilhører.
    public ArrayList<Wishlist> getAllWishlistsFromUserID(int userID){
        ArrayList<Wishlist> allWishLists = new ArrayList<>();
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM wishlists as wl WHERE wl.FK_user_id = "+userID);

            while (rs.next()){
                int wishlistID = rs.getInt("wishlist_id");
                int FKUserId = rs.getInt("FK_user_id");
                String title = rs.getString("title");
                allWishLists.add(new Wishlist(wishlistID,FKUserId,title));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } return allWishLists;
    }

    //Metode som tilføjer en ønskeliste til databasen. Der tages i mod en String og en integer og der returneres ikke noget.
    public void addWishlistToDatabase(String title, int userID){
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement();

            String query = "INSERT INTO `votum`.`wishlists` (`FK_user_id`, `title`) "
                    + "VALUES ('"+userID+"', '"+title+"');";

            stmt.executeUpdate(query);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
