package com.taskmaste.TaskMaste.Models;

public class SignUp {
    private String username;
    private String email;
    private String password;
    private String confirmpassword;

    public SignUp(String username, String email, String password, String confirmpassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public String getUserName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmpassword;
    }
}
