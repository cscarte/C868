package com.example.C868.Logins;

public class LoginUser {
    public String username;
    public String password;
    public boolean validUserPasswordCombo = false;

    public LoginUser(String username, String password, boolean validUserPasswordCombo) {
        this.username = username;
        this.password = password;
        this.validUserPasswordCombo = validUserPasswordCombo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidUserPasswordCombo() {
        return validUserPasswordCombo;
    }

    public boolean loginUser(String username, String password) {
        if (username.equals("user") && password.equals("user")) {
            validUserPasswordCombo = true;
        } else {
            validUserPasswordCombo = false;
        }
        return validUserPasswordCombo;
    }
}
