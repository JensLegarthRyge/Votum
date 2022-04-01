package com.example.votum.model.Services;

public class PasswordService {
    public static boolean isPasswordValid(String password){
        //Password must be between 8-16 characters.
        //Password must only consist og letters and numbers.
        //Password must consist of atleast 1 uppercase letter
        //Password must consist of atleast 1 lowercase letter
        //Password must consist of atleast 1 digit
        int length = password.length();
        boolean consistLowercase = false;
        boolean consistUppercase = false;
        boolean consistNumber = false;

        //Checks for length of password
        if (length < 8 || length > 16){
            return false;
        } else{
            //Goes through each charachter
            for (int i = 0; i < length; i++) {
                char currentChar = password.charAt(i);

                //Checks if is either a digit or letter
                if(Character.isDigit(currentChar) == false && Character.isLetter(currentChar) == false){
                    return false;
                }
                //If it is not a digit or letter it will not reach this statement
                if (Character.isDigit(currentChar) == true){
                    consistNumber = true;
                }
                //If not a digit, it must be a letter
                else {
                    if(Character.isUpperCase(currentChar)){
                        consistUppercase = true;
                    }
                    //If not uppercase it must be a lowercase
                    else {
                        consistLowercase = true;
                    }
                }
            }
            //If all conditions are true the password is valid. Else it will return false in the end of the method.
            if(consistLowercase == true && consistUppercase == true && consistNumber == true){
                return true;
            }
        }
            return false;
    }
}
