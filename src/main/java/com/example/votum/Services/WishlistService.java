package com.example.votum.Services;

import com.example.votum.Repositories.WishlistRepository;
import com.example.votum.model.Wishlist;

import java.util.ArrayList;

public class WishlistService {

    public static String currentWishlistID (String userID) {
        WishlistRepository wlr = new WishlistRepository();
        int userIDparsed = Integer.parseInt(userID);
        ArrayList<Wishlist> currentWishlistRepo = wlr.getAllWishlistsFromUserID(Integer.parseInt(userID));
        String currentWishlistIDString = "";

        for (int i = 0; i < currentWishlistRepo.size(); i++) {
            int currentUserID = currentWishlistRepo.get(i).getWishlistID();

            if (userIDparsed == currentUserID) {
                currentWishlistIDString += currentUserID;
            }
        }
        return currentWishlistIDString;
    }

}
