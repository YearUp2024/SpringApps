package com.taskmaste.TaskMaste.Models;

public class SignUp {
    private String username;
    private String email;
    private String password;

    public SignUp(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
