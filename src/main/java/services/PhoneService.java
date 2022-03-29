package services;

public class PhoneService {
    public static boolean isPhoneNumberValid(String email){
        if (email.contains("@") && email.contains(".")){
            return true;
        } else
            return false;
    }
}
