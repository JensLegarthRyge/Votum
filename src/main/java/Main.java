import Model.Repositories.UserRepository;
import Model.Repositories.WishRepository;
import Model.Repositories.WishlistRepository;
import Model.User;
import Model.Wish;
import Model.Wishlist;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        WishlistRepository wlr = new WishlistRepository();
        WishRepository wr = new WishRepository();
        UserRepository ur = new UserRepository();


        ArrayList<User> allUsers = ur.getAllUsers();
        for (User cr:allUsers) {
            System.out.println(cr.toString());
        }
        System.out.println("\n\n");


        ArrayList<Wishlist> allWishlists = wlr.getAllWishlistsFromUserID(3);
        for (Wishlist cwl:allWishlists) {
            System.out.println(cwl.getTitle());
        }
        System.out.println("\n\n");


        ArrayList<Wish> allWishes = wr.getAllWishesFromWishlistID(3);
        for (Wish cr:allWishes) {
            System.out.println(cr.toString());
        }
        System.out.println("\n\n");

    }
}
