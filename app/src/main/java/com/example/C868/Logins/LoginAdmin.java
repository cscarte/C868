package com.example.C868.Logins;

public class LoginAdmin extends LoginUser{
    private String username;
    private String password;
    private boolean validUserPasswordCombo = false;

    public LoginAdmin(String username, String password, boolean validUserPasswordCombo) {
        super(username, password, validUserPasswordCombo);
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
        if (username.equals("admin") && password.equals("admin")) {
            validUserPasswordCombo = true;
        } else {
            validUserPasswordCombo = false;
        }
        return validUserPasswordCombo;
    }
}
