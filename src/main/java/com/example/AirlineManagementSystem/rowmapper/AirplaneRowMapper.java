package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Airplane;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirplaneRowMapper implements RowMapper<Airplane> {

    @Override
    public Airplane mapRow(ResultSet rs, int rowNum) throws SQLException {
        Airplane airplane = new Airplane();
        airplane.setAirplaneId(rs.getInt("airplane_id"));
        airplane.setRegistrationNo(rs.getString("registration_no"));
        airplane.setDescription(rs.getString("description"));
        airplane.setModel(rs.getString("model"));
        airplane.setTotalSeats(rs.getInt("total_seats"));
        airplane.setTotalEconomySeats(rs.getInt("total_economy_seats"));
        airplane.setTotalBusinessSeats(rs.getInt("total_business_seats"));
        airplane.setTotalFirstClassSeats(rs.getInt("total_first_class_seats"));
        return airplane;
    }
}
