package com.example.votum.Repositories;

import com.example.votum.model.Wishlist;

import java.sql.*;
import java.util.ArrayList;

public class WishlistRepository {
    private final DatabaseConnectionManager dcm;

    public WishlistRepository() {
        dcm = new DatabaseConnectionManager();
    }

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

    public int getWishlistIDFromWishlistName(String wishlistName, int userID){
        ArrayList<Wishlist> allWishlists = getAllWishlistsFromUserID(userID);
        int wishlistID = 0;
        for (Wishlist cwl:allWishlists) {
            if (cwl.getTitle().equals(wishlistName)){
                return cwl.getWishlistID();
            }
        } return wishlistID;
    }

    public ArrayList<Integer> getAllWishListIDsFromUserID(int userID){
        ArrayList<Wishlist> allWishlists = getAllWishlistsFromUserID(userID);

        ArrayList<Integer> allWishlistIDs = new ArrayList<>();

        for (Wishlist cwl:allWishlists) {
            allWishlistIDs.add(cwl.getWishlistID());
        }
        return allWishlistIDs;
    }

}
