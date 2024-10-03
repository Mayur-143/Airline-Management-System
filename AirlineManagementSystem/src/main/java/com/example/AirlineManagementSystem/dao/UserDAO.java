package com.example.AirlineManagementSystem.dao;

import com.example.AirlineManagementSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Create User
    public void save(User user) {
        String sql = "INSERT INTO USERS (user_id, first_name, middle_name, last_name, password, date_of_birth, street, city, state, country, pin_code, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getPassword(),
                user.getDateOfBirth(), user.getStreet(), user.getCity(), user.getState(), user.getCountry(), user.getPinCode(), user.getRole());
    }

    // Get User by ID
    public User findById(int id) {
        String sql = "SELECT * FROM USERS WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToUser);
    }

    // Get All Users
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }

    // Helper method to map rows from ResultSet
    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setStreet(rs.getString("street"));
        user.setCity(rs.getString("city"));
        user.setState(rs.getString("state"));
        user.setCountry(rs.getString("country"));
        user.setPinCode(rs.getString("pin_code"));
        user.setRole(rs.getString("role"));
        return user;
    }
}

