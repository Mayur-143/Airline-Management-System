package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setMiddleName(rs.getString("middle_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setStreet(rs.getString("street"));
        employee.setCity(rs.getString("city"));
        employee.setState(rs.getString("state"));
        employee.setCountry(rs.getString("country"));
        employee.setPinCode(rs.getString("pin_code"));
        employee.setDesignation(rs.getString("designation"));
        employee.setMobileNumber(rs.getString("mobile_number"));
        employee.setAirportId(rs.getInt("airport_id"));
        return employee;
    }
}

