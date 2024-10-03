package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.dao.UserDAO;
import com.example.AirlineManagementSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void registerUser(User user) {
        userDAO.save(user);
    }

    public User getUserById(int id) {
        return userDAO.findById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
}