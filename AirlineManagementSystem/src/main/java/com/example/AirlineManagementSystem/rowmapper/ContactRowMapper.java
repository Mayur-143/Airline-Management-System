package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(rs.getInt("contact_id"));
        contact.setEmail(rs.getString("email"));
        contact.setName(rs.getString("name"));
        contact.setPhoneNumber(rs.getString("phone_number"));
        contact.setMessage(rs.getString("message"));
        contact.setUserId(rs.getInt("user_id"));
        return contact;
    }
}

