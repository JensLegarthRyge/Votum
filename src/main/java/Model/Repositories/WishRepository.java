package Model.Repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WishRepository {
    private final DatabaseConnectionManager dcm;

    public WishRepository() {
        dcm = new DatabaseConnectionManager();
    }

    public ArrayList<Wish> getAllWishesFromWishlistID(int WishlistID){
        ArrayList<Wish> allWishes = new ArrayList<>();
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM wishes as w WHERE w.FK_wishlist_id = "+WishlistID);

            while (rs.next()) {
                int wishID = rs.getInt("wish_id");
                String title = rs.getString("title");
                double price = rs.getDouble("price");
                String link = rs.getString("link");
                String description = rs.getString("description");
                boolean isReserved = rs.getInt("reserved") != 0;

                allWishes.add(new Wish(wishID,title,price,link,description,isReserved,WishlistID));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } return allWishes;
    }
}
