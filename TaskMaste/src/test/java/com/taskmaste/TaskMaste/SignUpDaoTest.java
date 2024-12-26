package com.taskmaste.TaskMaste;

import com.taskmaste.TaskMaste.DAO.interfaces.SignUpDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SignUpDaoTest {
    @Autowired
    private SignUpDao signUpDao;

    @Test
    void createUser_WithValidData_ReturnsTrue() {
        boolean result = signUpDao.createUser("test_user", "test@example.com", "Password123");
        assertTrue(result);
    }

    @Test
    void isUserNameAvailable_WithNewUsername_ReturnsTrue() {
        boolean result = signUpDao.isUserNameAvailable("new_user");
        assertTrue(result);
    }

    @Test
    void isUserNameAvailable_WithExistingUsername_ReturnsFalse() {
        signUpDao.createUser("existing_user", "existing@example.com", "Password123");
        boolean result = signUpDao.isUserNameAvailable("existing_user");
        assertFalse(result);
    }

    @Test
    void isEmailAvailable_WithNewEmail_ReturnsTrue() {
        boolean result = signUpDao.isEmailAvailable("new@example.com");
        assertTrue(result);
    }

    @Test
    void validatePassword_WithValidPassword_ReturnsTrue() {
        boolean result = signUpDao.validatePassword("Password123", "Password123");
        assertTrue(result);
    }

    @Test
    void validatePassword_WithShortPassword_ReturnsFalse() {
        boolean result = signUpDao.validatePassword("Short", "Short");
        assertFalse(result);
    }

    @Test
    void validateEmail_WithValidEmail_ReturnsTrue() {
        boolean result = signUpDao.validateEmail("valid@example.com");
        assertTrue(result);
    }

    @Test
    void validateEmail_WithInvalidEmail_ReturnsFalse() {
        boolean result = signUpDao.validateEmail("invalid.email");
        assertFalse(result);
    }
}
