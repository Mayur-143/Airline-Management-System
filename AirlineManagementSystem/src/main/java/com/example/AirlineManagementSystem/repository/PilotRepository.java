package com.example.AirlineManagementSystem.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import com.example.AirlineManagementSystem.rowmapper.PilotRowMapper;
import com.example.AirlineManagementSystem.model.Pilot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PilotRepository {

    private final JdbcTemplate jdbcTemplate;

    public PilotRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pilot> findAllPilots() {
        String sql = "SELECT * FROM PILOTS";
        return jdbcTemplate.query(sql, new PilotRowMapper());
    }

    // Additional methods for saving, updating, deleting pilots can be added here
}
