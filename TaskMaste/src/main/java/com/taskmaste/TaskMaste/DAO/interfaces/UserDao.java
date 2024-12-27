package com.taskmaste.TaskMaste.DAO.interfaces;

import com.taskmaste.TaskMaste.Models.User;

import java.util.List;

public interface UserDao {
    User getUserById(int userId);
    User getUserByUserName(String username);
    List<User> getAllUser();
    boolean createUser(String username, String email, String password);
    boolean updateUser(String username, String email, String password);
    boolean deleteUser(int userId);
    boolean validateUserCredentials(String username, String password);
    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);
}
