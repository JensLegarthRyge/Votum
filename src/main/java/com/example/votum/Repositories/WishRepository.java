package com.example.votum.Repositories;

import com.example.votum.model.Wish;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//I denne klasse har vi samlet de metoder som henter eller giver data videre der omhandler ønsker.
public class WishRepository {
    private final DatabaseConnectionManager dcm;

    //Constructor til klassen som bare laver en connection til databasen.
    public WishRepository() {
        dcm = new DatabaseConnectionManager();
    }

    //Metode som tager imod en integer og returnere en liste med ønsker som tilhører den ønskeliste der har det ID der kommer ind i parameteren.
    public ArrayList<Wish> getAllWishesFromWishlistID(int wishlistID){
        ArrayList<Wish> allWishes = new ArrayList<>();
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM wishes as w WHERE w.FK_wishlist_id = "+wishlistID);

            while (rs.next()) {
                int wishID = rs.getInt("wish_id");
                String title = rs.getString("title");
                double price = rs.getDouble("price");
                String link = rs.getString("link");
                String description = rs.getString("description");
                boolean isReserved = rs.getInt("reserved") != 0;

                allWishes.add(new Wish(wishID,title,price,link,description,isReserved,wishlistID));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } return allWishes;
    }

    //Meotde som tager imod et ønske og tilføjer det til databsen. Den returnere ingenting.
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

    //Metode som fjerne et ønske fra databasen baseret på det ID som den tager imod i parameteren.
    public void removeWishFromDatabase(int ID){
        try {
            Connection con = dcm.getConnectionToDatabase();
            Statement stmt = con.createStatement();

            String query = "DELETE FROM `votum`.`wishes` WHERE `wish_id` = '"+ID+"'";
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
