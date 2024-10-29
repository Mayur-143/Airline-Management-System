package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Airport;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportRowMapper implements RowMapper<Airport> {

    @Override
    public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
        Airport airport = new Airport();
        airport.setAirportId(rs.getInt("airport_id"));
        airport.setAirportName(rs.getString("airport_name"));
        airport.setIATA_code(rs.getString("IATA_code"));
        airport.setLocation(rs.getString("location"));
        airport.setAddress(rs.getString("address"));
        return airport;
    }
}
