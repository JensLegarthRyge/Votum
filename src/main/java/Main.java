import Model.Repositories.UserRepository;
import Model.User;

public class Main {
    public static void main(String[] args) {
        UserRepository ur = new UserRepository();
        /*
        WishlistRepository wlr = new WishlistRepository();
        WishRepository wr = new WishRepository();


        ArrayList<User> allUsers = ur.getAllUsers();
        for (User cr:allUsers) {
            System.out.println(cr.toString());
            System.out.println(cr.getBirthDate());

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

        Wish emilsØnske = new Wish(0,"Bil",10.00,"mercedes.com","super lang fed mercedes der hedder maybach"
        ,true,5);

        wr.addWishToDatabase(emilsØnske);
         */

        User sarah = new User(1,"sarahengsted@gmail.com","SarahSejesen123","1998-10-10","Sarah","Engsted Andreasen","60501522");
        System.out.println(ur.isLoginValid("sarahengsted@gmail.com", "SarahSejesen123"));
        System.out.println(ur.isMailTaken("SarahENGSTED@gmail.com"));


    }
}
