package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.rowmapper.UserRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new user
    public int saveUser(User user) {
        String sql = "INSERT INTO USERS (user_id, first_name, middle_name, last_name, password, date_of_birth, street, city, state, country, pin_code, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getUserId(), user.getFirstName(), user.getMiddleName(),
                user.getLastName(), user.getPassword(), user.getDateOfBirth(),
                user.getStreet(), user.getCity(), user.getState(),
                user.getCountry(), user.getPinCode(), user.getRole());
    }

    // Update an existing user
    public int updateUser(User user) {
        String sql = "UPDATE USERS SET first_name = ?, middle_name = ?, last_name = ?, password = ?, date_of_birth = ?, " +
                "street = ?, city = ?, state = ?, country = ?, pin_code = ?, role = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql,
                user.getFirstName(), user.getMiddleName(), user.getLastName(),
                user.getPassword(), user.getDateOfBirth(), user.getStreet(),
                user.getCity(), user.getState(), user.getCountry(),
                user.getPinCode(), user.getRole(), user.getUserId());
    }

    // Find a user by ID
    public User findUserById(int userId) {
        String sql = "SELECT * FROM USERS WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowmapper(), userId);
    }

    // Delete a user by ID
    public int deleteUserById(int userId) {
        String sql = "DELETE FROM USERS WHERE user_id = ?";
        return jdbcTemplate.update(sql, userId);
    }

    // Find all users
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, new UserRowmapper());
    }
}