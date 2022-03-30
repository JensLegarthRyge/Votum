package Model.Repositories;

import Model.Wishlist;

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

}
