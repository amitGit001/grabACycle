package com.grabACycle.grabACycle.controller;

import com.grabACycle.grabACycle.entity.User;
import com.grabACycle.grabACycle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public List<User> getAllUsers()
    {
        return userService.fetchUsers();
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUserById(@PathVariable int userId)
    {
        return userService.fetchUserById(userId);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @PatchMapping("/user/{userId}")
    public User updateUserById(@PathVariable int userId, @PathVariable User user)
    {
        return userService.updateUserById(userId, user);
    }

    @DeleteMapping("/user")
    public void deleteUser(@PathVariable int userId)
    {
        userService.deleteUser(userId);
    }
}
