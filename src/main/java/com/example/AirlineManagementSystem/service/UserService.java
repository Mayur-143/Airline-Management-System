package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // Add a new user
   


    @Override
    public UserDetails loadUserByUsername(String primaryEmail) throws UsernameNotFoundException {
        System.out.println("Loading user by email: " + primaryEmail);  // Debug log
        // Retrieve user by email from the database
        User user = userRepository.findByPrimaryEmail(primaryEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + primaryEmail));
        System.out.println("User found: " + user.getPrimaryEmail() + ", Stored Password Hash: " + user.getPassword());
        // Build Spring Security's UserDetails object
        return org.springframework.security.core.userdetails.User.withUsername(user.getPrimaryEmail())
            .password(user.getPassword())   // Retrieve encoded password from the database
            .roles(user.getRole())          // Assign role from the database
            .build();
}

    

    public User addUser(User user) {
        System.out.println("Adding user: " + user.getPrimaryEmail());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.saveUser(user);  // Save the user and get the generated userId
        userRepository.saveUserEmail(user.getUserId(), user.getPrimaryEmail());  // Save the primary email using the generated userId
        return user;
    }
    public boolean primaryEmailExists(String primaryEmail) {
        return userRepository.findPrimaryEmail(primaryEmail);
    }

    public Optional<User> findUserByPrimaryEmail(String primaryEmail) {
        return userRepository.findByPrimaryEmail(primaryEmail);
    }

    // Update an existing user
    public User updateUser(User user) {
        // Validate the user update request
        userRepository.updateUser(user);
        return user;
    }

    // Find a user by ID
    public Optional<User> getUserById(int userId) {
        User user = userRepository.findUserById(userId);
        return Optional.ofNullable(user); // Return Optional for better null handling
    }

    // Delete a user by ID
    public boolean deleteUserById(int userId) {
        int rowsAffected = userRepository.deleteUserById(userId);
        return rowsAffected > 0; // Return true if the user was successfully deleted
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }
    
}