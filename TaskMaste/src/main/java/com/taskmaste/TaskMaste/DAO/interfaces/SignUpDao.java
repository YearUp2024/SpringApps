package com.taskmaste.TaskMaste.DAO.interfaces;

public interface SignUpDao {
    boolean createUser(String username, String email, String password);
    boolean isUserNameAvailable(String username);
    boolean isEmailAvailable(String email);
    boolean validatePassword(String password, String confirmPassword);
    boolean validateEmail(String email);
}
