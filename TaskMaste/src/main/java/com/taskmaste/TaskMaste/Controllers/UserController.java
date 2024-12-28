package com.taskmaste.TaskMaste.Controllers;

import com.taskmaste.TaskMaste.DAO.interfaces.UserDao;
import com.taskmaste.TaskMaste.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userDao.getUserByUserName(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> isUserCreated(@RequestBody User user){
        boolean userCreated = userDao.createUser(user.getUsername(), user.getEmail(), user.getPassword());

        if(userCreated){
            return ResponseEntity.ok("User Created Successfully!");
        }
        return ResponseEntity.badRequest().body("User was not able to be Created!");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> isUpdateUser(@RequestBody User user, @PathVariable int userId){
        boolean updateUser = userDao.updateUser(user.getUsername(), user.getEmail(), user.getPassword(), userId);

        if(updateUser){
            return ResponseEntity.ok("User Updated Successfully!");
        }
        return ResponseEntity.badRequest().body("User was not able to be Updated!");
    }
}
