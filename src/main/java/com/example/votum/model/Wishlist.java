package com.example.votum.model;
/*Wishlist klassen bliver brugt til at lave ønskelister.
Der er ikke så meget funktionalitet herinde, bare attributter, contructor,   og getters.
* */
public class Wishlist {
    private int wishlistID;
    private int FKUserId;
    private String title;

    public Wishlist(int wishlistID, int FKUserId, String title) {
        this.wishlistID = wishlistID;
        this.FKUserId = FKUserId;
        this.title = title;
    }

    public Wishlist(int FKUserId, String title) {
        this.FKUserId = FKUserId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public int getFKUserId() {
        return FKUserId;
    }

    @Override
    public String toString() {
        return ""+wishlistID;
    }
}
