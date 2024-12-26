package com.taskmaste.TaskMaste.Controllers;

import com.taskmaste.TaskMaste.DAO.interfaces.LoginDao;
import com.taskmaste.TaskMaste.Models.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginDao loginDao;

    @Autowired
    public LoginController(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody Login login){
        boolean loggedIn = loginDao.validateLogin(login.getUsername(), login.getPassword());

        if(loggedIn){
            return ResponseEntity.ok("Logged in Successfully!");
        }
        return ResponseEntity.badRequest().body("Logged in Failed!");
    }
}
