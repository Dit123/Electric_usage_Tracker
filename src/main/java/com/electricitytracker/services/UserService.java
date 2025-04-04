package com.electricitytracker.services;

import com.electricitytracker.models.User;
import com.electricitytracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User already exists!");
        }
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, hashedPassword);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        user.incrementLoginCount();
        return userRepository.save(user);
    }

//     public String generateResetToken(String email) {
//     Optional<User> userOptional = userRepository.findByEmail(email);
//     if (userOptional.isEmpty()) {
//         return "User not found";
//     }

//     User user = userOptional.get();
//     String token = UUID.randomUUID().toString();
//     user.setResetToken(token);
//     user.setTokenExpiry(LocalDateTime.now().plusMinutes(15));
//     userRepository.save(user);

//     String resetLink = "http://localhost:9090/api/users/reset-password?token=" + token;

//     emailService.sendEmail(user.getEmail(), "Password Reset Request",
//         "Click the link to reset your password: " + resetLink);


//     return "Password reset link sent to email";
// }

// public String resetPassword(String token, String newPassword) {
//     Optional<User> userOptional = userRepository.findByResetToken(token);
//     if (userOptional.isEmpty()) {
//         return "Invalid";
//     }

//     User user = userOptional.get();

//     if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
//         return "Token expired. Request a new one.";
//     }

//     user.setPassword(passwordEncoder.encode(newPassword));
//     user.setResetToken(null); 
//     user.setTokenExpiry(null);
//     userRepository.save(user);

//     return "Password reset successful";
// }

 }
