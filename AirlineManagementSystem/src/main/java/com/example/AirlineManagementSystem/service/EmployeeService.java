package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.model.Employee;
import com.example.AirlineManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Fetch all employees from the database
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Add more methods as needed (e.g., addEmployee, updateEmployee, deleteEmployee)
}

