package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Contact;
import com.example.AirlineManagementSystem.rowmapper.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to save a new contact
    public int save(Contact contact) {
        String sql = "INSERT INTO CONTACT (email, name, phone_number, message, user_id) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
                contact.getEmail(),
                contact.getName(),
                contact.getPhoneNumber(),
                contact.getMessage(),
                contact.getUserId());
    }

    // Method to find a contact by ID
    public Optional<Contact> findById(int contactId) {
        String sql = "SELECT * FROM CONTACT WHERE contact_id = ?";
        return jdbcTemplate.query(sql, new ContactRowMapper(), contactId)
                           .stream()
                           .findFirst();
    }

    // Method to find all contacts
    public List<Contact> findAll() {
        String sql = "SELECT * FROM CONTACT";
        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    // Method to update a contact
    public int update(Contact contact) {
        String sql = "UPDATE CONTACT SET email = ?, name = ?, phone_number = ?, message = ?, user_id = ? WHERE contact_id = ?";
        return jdbcTemplate.update(sql,
                contact.getEmail(),
                contact.getName(),
                contact.getPhoneNumber(),
                contact.getMessage(),
                contact.getUserId(),
                contact.getContactId());
    }

    // Method to delete a contact by ID
    public int deleteById(int contactId) {
        String sql = "DELETE FROM CONTACT WHERE contact_id = ?";
        return jdbcTemplate.update(sql, contactId);
    }
}
