package com.example.AirlineManagementSystem.controller;

import com.example.AirlineManagementSystem.model.Airport;
import com.example.AirlineManagementSystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    // Show the form to add a new airport
    @GetMapping("/add")
    public String showAddAirportForm(Model model) {
        model.addAttribute("airport", new Airport());
        return "add-airport";
    }

    // Process the form to add a new airport
    @PostMapping("/add")
    public String addAirport(@ModelAttribute("airport") Airport airport) {
        airportService.addAirport(airport);
        return "redirect:/admin/airports/view";  // Redirect to view page after saving
    }

    // Display the list of all airports
    @GetMapping("/view")
    public String viewAllAirports(Model model) {
        List<Airport> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        return "view-airports";
    }
}

