package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowmapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
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
