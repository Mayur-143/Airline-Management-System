package com.example.AirlineManagementSystem.service;

//import com.example.AirlineManagementSystem.dao.UserDAO;
import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Add a new user
    public User addUser(User user) {
        // Additional business logic can go here, such as hashing the password
        userRepository.saveUser(user);
        return user;
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