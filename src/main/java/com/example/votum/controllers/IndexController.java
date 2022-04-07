package com.example.votum.controllers;

import com.example.votum.Repositories.UserRepository;
import com.example.votum.Repositories.WishRepository;
import com.example.votum.Repositories.WishlistRepository;
import com.example.votum.Services.WishlistService;
import com.example.votum.model.User;
import com.example.votum.model.Wish;
import com.example.votum.model.Wishlist;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController {
    @GetMapping("/")
    public String frontPage(HttpSession session) {
        if (null != session.getAttribute("userID")) {
            return "redirect:/logged-in-frontpage";
        } else{
            return "index";
        }
    }

    @GetMapping("/logged-in-frontpage")
    public String loggedInFrontpage(HttpSession session, Model wishlistModel){
        WishlistRepository wlr = new WishlistRepository();

        ArrayList<Wishlist> allUserWishlists = wlr.getAllWishlistsFromUserID((int)session.getAttribute("userID"));

        wishlistModel.addAttribute("allWishLists",allUserWishlists);

        return "frontPage";
    }

    @PostMapping("/login-user")
    public String loginInfoUser(WebRequest dataFromForm, HttpSession session) {
        String email = dataFromForm.getParameter("email-ting");
        String password = dataFromForm.getParameter("psw");

        UserRepository ur = new UserRepository();
        if (ur.isLoginValid(email, password)){
            User currentUser = ur.getUserFromEmail(email);
            session.setAttribute("userID",currentUser.getUserID());
            return "redirect:/logged-in-frontpage";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping ("/removeWish")
    public String removeWish(WebRequest dataFromForm,HttpSession session){
        WishRepository rp = new WishRepository();
        int currentWishID = Integer.parseInt(dataFromForm.getParameter("hiddenID"));
        rp.removeWishFromDatabase(currentWishID);
        return "redirect:/list";

    }

    @PostMapping("/create-user")
    public String createUserInfo(WebRequest dataFromForm,HttpSession session) {
        String email = dataFromForm.getParameter("create-email");
        String password = dataFromForm.getParameter("create-psw");
        String birthday = dataFromForm.getParameter("birthday");
        String surName = dataFromForm.getParameter("first-name");
        String lastName = dataFromForm.getParameter("last-name");
        String phoneNumber = dataFromForm.getParameter("tlf-number");


        UserRepository ur = new UserRepository();
        if (!ur.isMailTaken(email)) {
            User temp = new User(email,password,birthday,surName,lastName,phoneNumber);
            ur.addUserToDatabase(temp);
            User currentUser = ur.getUserFromEmail(email);
            session.setAttribute("userID",currentUser.getUserID());
            return "redirect:/logged-in-frontpage";
        } else {
            return "redirect:/ ";
        }
    }

    @PostMapping("/create-wishlist")
    public String wishListCreator (WebRequest dataFromForm, HttpSession session) {
        WishlistRepository wlr = new WishlistRepository();

        String wishListName = dataFromForm.getParameter("name-for-wishlist");

        wlr.addWishlistToDatabase(wishListName,(int)session.getAttribute("userID"));

        session.setAttribute("wishlistID", WishlistService.currentWishlistID(session.getAttribute("userID").toString()));

        return "redirect:/logged-in-frontpage";
    }

    @PostMapping("/create-wish")
    public String wishCreator (WebRequest dataFromForm, HttpSession session) {
        WishRepository wr = new WishRepository();

        String title = dataFromForm.getParameter("name-for-wish");
        String priceString = dataFromForm.getParameter("price-of-wish");
        double price = Double.parseDouble(priceString);
        String url = dataFromForm.getParameter("link-for-wish");
        String description = dataFromForm.getParameter("description-of-wish");
        int wishlistID = (int)(session.getAttribute("wishlistID"));

        Wish newWish = new Wish(title, price, url, description, wishlistID);
        wr.addWishToDatabase(newWish);

        return "redirect:/list";
    }

    @GetMapping("/404-error")
    public String handleError(HttpServletRequest request, HttpSession session) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }
        return "error";
    }

    @GetMapping("/cookiepolitik")
    public String cookiepolitik(HttpSession session){
        return "cookiepolitik";
    }

    @GetMapping("/privatlivspolitik")
    public String privatlivspolitik(HttpSession session){
        return "privatlivspolitik";
    }

    @GetMapping("/kontakt")
    public String kontakt(HttpSession session){
        return "kontakt";
    }

    @GetMapping("/jobOgKarriere")
    public String jobOgKarriere(HttpSession session){
        return "jobOgKarriere";
    }

    @RequestMapping("/list")
    public String list(HttpSession session, Model allWishesForWishlist, WebRequest dataFromForm){
        WishRepository rp = new WishRepository();
        int currentWishlistID = 0;
        if(dataFromForm.getParameter("hidden") != null){
            currentWishlistID = Integer.parseInt(dataFromForm.getParameter("hidden"));
            session.setAttribute("wishlistID", currentWishlistID);
        }
        else {
            currentWishlistID =  Integer.parseInt(session.getAttribute("wishlistID").toString());
        }
        ArrayList<Wish> wishes = rp.getAllWishesFromWishlistID(currentWishlistID);
        allWishesForWishlist.addAttribute("allWishes", wishes);
        return "list";
    }
   @PostMapping("/log-ud")
   public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/";
   }



}
