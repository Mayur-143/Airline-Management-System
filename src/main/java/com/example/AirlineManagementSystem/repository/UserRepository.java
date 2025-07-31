package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.rowmapper.UserRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public User saveUser(User user) {
        String sql = "INSERT INTO USERS (first_name, middle_name, last_name, password, date_of_birth, street, city, state, country, pin_code, role, primary_email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Use KeyHolder to retrieve the auto-generated userId
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "user_id" });
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getMiddleName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
            ps.setString(6, user.getStreet());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getCountry());
            ps.setString(10, user.getPinCode());
            ps.setString(11, user.getRole());
            ps.setString(12, user.getPrimaryEmail());
            return ps;
        }, keyHolder);

        // Set the generated userId back to the User object
        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    // Update an existing user
    public int updateUser(User user) {
        String sql = "UPDATE USERS SET first_name = ?, middle_name = ?, last_name = ?, password = ?, date_of_birth = ?, " +
                "street = ?, city = ?, state = ?, country = ?, pin_code = ?, role = ?, primary_email=?  WHERE user_id = ?";
        return jdbcTemplate.update(sql,
                user.getFirstName(), user.getMiddleName(), user.getLastName(),
                user.getPassword(), user.getDateOfBirth(), user.getStreet(),
                user.getCity(), user.getState(), user.getCountry(),
                user.getPinCode(), user.getRole(), user.getUserId(), user.getPrimaryEmail());
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


    public boolean findEmailByUserId(int userId, String email) {
        String sql = "SELECT COUNT(*) FROM USER_EMAIL WHERE user_id = ? AND email = ?";
        Integer count = jdbcTemplate.queryForObject(sql,(rs, rowNum) -> rs.getInt(1),  new Object[]{userId, email});
        return count != null && count > 0; // Return true if there are any matching records
    }

    public void saveUserEmail(int userId, String email) {
        String sql = "INSERT INTO USER_EMAIL (user_id, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, email);
    }

    public boolean findPrimaryEmail(String email) {
        String sql = "SELECT COUNT(*) FROM USERS WHERE primary_email = ?";
        Integer count = jdbcTemplate.queryForObject(sql,(rs, rowNum) -> rs.getInt(1), new Object[]{email});
        return count != null && count > 0; // Return true if email exists
    }

    public Optional<User> findByPrimaryEmail(String email) {
        System.out.println("Searching for email: " + email);
        String sql = "SELECT * FROM USERS WHERE primary_email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new UserRowmapper(), email);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    
}