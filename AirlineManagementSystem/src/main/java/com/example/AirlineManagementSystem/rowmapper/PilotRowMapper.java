package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Pilot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PilotRowMapper implements RowMapper<Pilot> {

    @Override
    public Pilot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pilot pilot = new Pilot();

        pilot.setPilotId(rs.getInt("pilot_id"));
        pilot.setFirstName(rs.getString("first_name"));
        pilot.setMiddleName(rs.getString("middle_name"));
        pilot.setLastName(rs.getString("last_name"));
        pilot.setDateOfBirth(rs.getDate("date_of_birth")); // Use getDate for DATE
        pilot.setLicenseNo(rs.getString("license_no"));
        pilot.setFlightId(rs.getInt("flight_id"));
        pilot.setCountry(rs.getString("country"));
        pilot.setState(rs.getString("state"));
        pilot.setCity(rs.getString("city"));
        pilot.setPincode(rs.getInt("pincode"));
        pilot.setStreet(rs.getString("street"));
        
        return pilot;
    }
}

