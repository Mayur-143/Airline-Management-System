package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Employee;
import com.example.AirlineManagementSystem.rowmapper.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to save a new employee
    public int save(Employee employee) {
        String sql = "INSERT INTO EMPLOYEE (first_name, middle_name, last_name, street, city, state, country, pin_code, designation, mobile_number, airport_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName(),
                employee.getStreet(),
                employee.getCity(),
                employee.getState(),
                employee.getCountry(),
                employee.getPinCode(),
                employee.getDesignation(),
                employee.getMobileNumber(),
                employee.getAirportId());
    }

    // Method to find an employee by ID
    public Optional<Employee> findById(int employeeId) {
        String sql = "SELECT * FROM EMPLOYEE WHERE employee_id = ?";
        return jdbcTemplate.query(sql, new EmployeeRowMapper(), employeeId)
                           .stream()
                           .findFirst();
    }

    // Method to find all employees
    public List<Employee> findAll() {
        String sql = "SELECT * FROM EMPLOYEE";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    // Method to update an employee
    public int update(Employee employee) {
        String sql = "UPDATE EMPLOYEE SET first_name = ?, middle_name = ?, last_name = ?, street = ?, city = ?, state = ?, country = ?, pin_code = ?, designation = ?, mobile_number = ?, airport_id = ? WHERE employee_id = ?";
        return jdbcTemplate.update(sql,
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName(),
                employee.getStreet(),
                employee.getCity(),
                employee.getState(),
                employee.getCountry(),
                employee.getPinCode(),
                employee.getDesignation(),
                employee.getMobileNumber(),
                employee.getAirportId(),
                employee.getEmployeeId());
    }

    // Method to delete an employee by ID
    public int deleteById(int employeeId) {
        String sql = "DELETE FROM EMPLOYEE WHERE employee_id = ?";
        return jdbcTemplate.update(sql, employeeId);
    }
}

