package Model.Services;

public class PhoneService {
    public static boolean isPhoneNumberValid(String number){
        //Checks that the length is 8 (as all danish phone numbers are).
        if (number.length() != 8){
            return false;
        } else{
            //Goes through all characters and checks wether they are a digit or not.
            for (int i = 0; i < number.length(); i++) {
                char currentChar = number.charAt(i);
                if(Character.isDigit(currentChar) == false){
                    return false;
                }
            }
        }
        return true;
    }
}
