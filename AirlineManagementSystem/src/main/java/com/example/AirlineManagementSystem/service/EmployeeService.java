package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.model.Employee;
import com.example.AirlineManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Fetch all employees
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Find employee by ID
    public Optional<Employee> findById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    // Add a new employee
    public int addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update an existing employee
    public int updateEmployee(Employee employee) {
        return employeeRepository.update(employee);
    }

    // Delete an employee by ID
    public int deleteEmployee(int employeeId) {
        return employeeRepository.deleteById(employeeId);
    }
}
