package Model;

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
}
