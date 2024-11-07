package com.example.AirlineManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AirlineManagementSystem.model.Airport;
import com.example.AirlineManagementSystem.model.Employee;
import com.example.AirlineManagementSystem.service.AirportService;
import com.example.AirlineManagementSystem.service.EmployeeService;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AirportService airportService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, AirportService airportService) {
        this.employeeService = employeeService;
        this.airportService = airportService;
    }

    // Show form to register a new employee
    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        Employee employee = new Employee();
        employee.setAirportId(0);
        model.addAttribute("employee", new Employee());
        List<Airport> airports = airportService.getAllAirports();
        System.out.println("Airports List: " + airports);  // Debugging line
        System.out.println("Employee airportId: " + employee.getAirportId());
        model.addAttribute("airports", airports); // Pass airports list for dropdown
        return "employeeRegister";
    }

    // Process the employee registration form
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/admin/employees/view";  // Redirect to view all employees after saving
    }

    // Display all employees
    @GetMapping("/view")
    public String viewAllEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "view-employees";
    }
}
