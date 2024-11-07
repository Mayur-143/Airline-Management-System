package com.example.AirlineManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AirlineManagementSystem.model.Airplane;
import com.example.AirlineManagementSystem.service.AirplaneService;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    // Show the form to add a new airplane
    @GetMapping("/add")
    public String showAddAirplaneForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        return "add-airplane";
    }

    // Process the form to add a new airplane
    @PostMapping("/add")
    public String addAirplane(@ModelAttribute("airplane") Airplane airplane) {

        int totalSeats = airplane.getTotalEconomySeats() + airplane.getTotalBusinessSeats() + airplane.getTotalFirstClassSeats();
        airplane.setTotalSeats(totalSeats);
        airplaneService.addAirplane(airplane);
        return "redirect:/airplanes/view";  // Redirect to view page after saving
    }

    // Display the list of all airplanes
    @GetMapping("/view")
    public String viewAllAirplanes(Model model) {
        List<Airplane> airplanes = airplaneService.getAllAirplanes();
        model.addAttribute("airplanes", airplanes);
        return "view-airplanes";
    }
}

