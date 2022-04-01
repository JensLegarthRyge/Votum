package com.example.votum.model.Repositories;

import com.example.votum.model.Wish;

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

    public void addWishToDatabase(Wish wish){
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement();

            String title = wish.getTitle();
            double price = wish.getPrice();
            String link = wish.getLink();
            String description = wish.getDescription();
            int reserved = 1;
            if (wish.isReserved()){
                reserved = 0;
            }
            int FKWishlistID = wish.getFKWishlistID();

            String query = "INSERT INTO `votum`.`wishes` (`title`, `price`, `link`, `description`, `reserved`, `FK_wishlist_id`)"
                    + "VALUES ('"+title+"', '"+price+"', '"+link+"', '"+description+"', '"+reserved+"', '"+FKWishlistID+"')";

            stmt.executeUpdate(query);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
