package Services;

public class PasswordService {
    public static boolean isPasswordValid(String email){
        if (email.contains("@") && email.contains(".")){
            return true;
        } else
            return false;
    }
}
