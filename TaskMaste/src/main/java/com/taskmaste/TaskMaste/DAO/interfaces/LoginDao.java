package com.taskmaste.TaskMaste.DAO.interfaces;

public interface LoginDao {
    boolean validateLogin(String username, String password);
    boolean checkUserExists(String username);
    String getStoredPassword(String password);
}
