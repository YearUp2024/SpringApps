package com.taskmaste.TaskMaste.DAO.interfaces;

import com.taskmaste.TaskMaste.Models.User;

public interface UserDao {
    User getUserByUserName(String username);
    boolean createUser(String username, String email, String password);
    boolean updateUser(String username, String email, String password, int userId);
    boolean deleteUser(int userId);
    boolean validateUserCredentials(String username, String password);
    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);
}
