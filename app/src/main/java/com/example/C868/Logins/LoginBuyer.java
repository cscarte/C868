package com.example.C868.Logins;

public class LoginBuyer extends LoginUser {


    public LoginBuyer(String username, String password, boolean validUserPasswordCombo) {
        super(username, password, validUserPasswordCombo);
    }

    public boolean loginUser(String username, String password) {
        if (username.equals("buyer") && password.equals("buyer")) {
            validUserPasswordCombo = true;
        } else {
            validUserPasswordCombo = false;
        }
        return validUserPasswordCombo;
    }
}
