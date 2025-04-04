package com.electricitytracker.controllers;

import com.electricitytracker.models.User;
import com.electricitytracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }

    // @PostMapping("/forgot-password")
    // public String forgotPassword(@RequestParam String email) {
    //     return userService.generateResetToken(email);
    // }
    
    // @PostMapping("/reset-password")
    // public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
    //     return userService.resetPassword(token, newPassword);
    // }

}
