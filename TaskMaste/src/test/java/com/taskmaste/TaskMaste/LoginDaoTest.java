package com.taskmaste.TaskMaste;

import com.taskmaste.TaskMaste.DAO.interfaces.LoginDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginDaoTest {
    @Autowired
    private LoginDao loginDao;

    @Test
    void validateLogin_WithValidCredentials_ReturnsTrue() {
        boolean result = loginDao.validateLogin("test_user", "Password123");
        assertTrue(result);
    }

    @Test
    void validateLogin_WithInvalidCredentials_ReturnsFalse() {
        boolean result = loginDao.validateLogin("wrong_user", "WrongPass123");
        assertFalse(result);
    }

    @Test
    void checkUserExists_WithExistingUser_ReturnsTrue() {
        boolean result = loginDao.checkUserExists("test_user");
        assertTrue(result);
    }

    @Test
    void checkUserExists_WithNonExistingUser_ReturnsFalse() {
        boolean result = loginDao.checkUserExists("nonexistent_user");
        assertFalse(result);
    }

    @Test
    void getStoredPassword_ReturnsCorrectPassword() {
        String password = loginDao.getStoredPassword("test_user");
        assertEquals("Password123", password);
    }
}