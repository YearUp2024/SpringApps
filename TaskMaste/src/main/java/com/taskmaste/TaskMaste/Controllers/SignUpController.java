package com.taskmaste.TaskMaste.Controllers;

import com.taskmaste.TaskMaste.DAO.interfaces.SignUpDao;
import com.taskmaste.TaskMaste.Models.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private SignUpDao signUpDao;

    @Autowired
    public SignUpController(SignUpDao signUpDao) {
        this.signUpDao = signUpDao;
    }

    @PostMapping()
    public ResponseEntity<String> getSignUp(@RequestBody SignUp signUp){
        boolean isCreate = signUpDao.createUser(
                signUp.getUserName(),
                signUp.getEmail(),
                signUp.getPassword()
        );
        if(isCreate){
            return ResponseEntity.ok("User Created Successfully!");
        }
        return ResponseEntity.badRequest().body("Failed to create users");
    }
}
