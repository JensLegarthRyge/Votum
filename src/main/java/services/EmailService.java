package services;

public class EmailService {
    public static boolean isEmailValid(String email){
        if (email.contains("@") && email.contains(".")){
            return true;
        } else
            return false;
    }
}
