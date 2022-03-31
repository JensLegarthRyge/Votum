package Model.Services;

import Model.Repositories.UserRepository;
import Model.User;

import java.util.ArrayList;

public class LoginService {
    public boolean isEmailTaken(String emailToCheck) {
        UserRepository ur = new UserRepository();
        ArrayList<User> allUsers = ur.getAllUsers();

        boolean isTaken = false;
        for (User cu : allUsers) {
            if (cu.getEmail().equalsIgnoreCase(emailToCheck)) {
                isTaken = true;
            }
        } return isTaken;
    }


    //Not relevant anymore due to  validation done on data level now
    public boolean isLoginValid(String email, String password){
        UserRepository ur = new UserRepository();
        ArrayList<User> allUsers = ur.getAllUsers();

        boolean isValidUser = false;
        for (User cu : allUsers) {
            if (cu.getEmail().equalsIgnoreCase(email)
                    && cu.getPassword().equals(password)) {
                isValidUser = true;
            }
        } return isValidUser;
    }
}
