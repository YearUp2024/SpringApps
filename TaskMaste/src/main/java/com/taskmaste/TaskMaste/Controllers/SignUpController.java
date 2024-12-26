package com.taskmaste.TaskMaste.Controllers;

import com.taskmaste.TaskMaste.DAO.interfaces.SignUpDao;
import com.taskmaste.TaskMaste.Models.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final SignUpDao signUpDao;

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

    @PostMapping("/check-username")
    public ResponseEntity<String> checkUsername(@RequestBody SignUp signUp){
        boolean usernameAvailable = signUpDao.isUserNameAvailable(signUp.getUserName());

        if(usernameAvailable){
            return ResponseEntity.ok("Username is available!");
        }
        return ResponseEntity.badRequest().body("Username is not available!");
    }

    @PostMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody SignUp signUp){
        boolean emailAvailable = signUpDao.isEmailAvailable(signUp.getEmail());

        if(emailAvailable){
            return ResponseEntity.ok("Email is available!");
        }
        return ResponseEntity.badRequest().body("Email is not available!");
    }
}
