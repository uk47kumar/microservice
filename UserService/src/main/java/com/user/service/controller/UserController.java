package com.user.service.controller;

import com.user.service.entity.User;
import com.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // create
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    // all user get
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    // single user get
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId")String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

}
