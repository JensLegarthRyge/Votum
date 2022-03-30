package Model;

public class Wishlist {
    private int wishlistID;
    private int FKUserId;
    private String title;

    public Wishlist(int wishlistID, int FKUserId, String title) {
        this.wishlistID = wishlistID;
        this.FKUserId = FKUserId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
