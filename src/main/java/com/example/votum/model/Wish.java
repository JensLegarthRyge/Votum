package com.example.votum.model;

public class Wish {
    private int wishID;
    private String title;
    private double price;
    private String link;
    private String description;
    private boolean reserved;
    private int FKWishlistID;

    public Wish(int wishID, String title, double price, String link, String description, boolean reserved, int FKWishlistID) {
        this.wishID = wishID;
        this.title = title;
        this.price = price;
        this.link = link;
        this.description = description;
        this.reserved = reserved;
        this.FKWishlistID = FKWishlistID;
    }

    public Wish(String title, double price, String link, String description, int FKWishlistID) {
        this.title = title;
        this.price = price;
        this.link = link;
        this.description = description;
        this.FKWishlistID = FKWishlistID;
    }

    public Wish(int wishID, int FKWishlistID) {
        this.wishID = wishID;
        this.FKWishlistID = FKWishlistID;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "wishID=" + wishID +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", reserved=" + reserved +
                ", FKWishlistID=" + FKWishlistID +
                '}';
    }

    public int getWishID(){
        return wishID;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getLink() {
        if (link.contains("http://www.")){
            return link;
        }
        else {
            return "http://www." + link;
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean isReserved() {
        return reserved;
    }

    public int getFKWishlistID() {
        return FKWishlistID;
    }
}
