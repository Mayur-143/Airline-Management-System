package com.example.AirlineManagementSystem.controller;

import com.example.AirlineManagementSystem.dto.FlightDTO;
import com.example.AirlineManagementSystem.model.Airplane;
import com.example.AirlineManagementSystem.model.Airport;
import com.example.AirlineManagementSystem.model.Flight;
import com.example.AirlineManagementSystem.service.AirplaneService;
import com.example.AirlineManagementSystem.service.AirportService;
import com.example.AirlineManagementSystem.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final AirportService airportService;
    private final AirplaneService airplaneService;

    public FlightController(FlightService flightService, AirportService airportService, AirplaneService airplaneService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.airplaneService = airplaneService;
    }

    // Show Add Flight Form
    @GetMapping("/add")
    public String showAddFlightForm(Model model) {
        Flight flight = new Flight();
        List<Airport> airports = airportService.getAllAirports();
        List<Airplane> airplanes = airplaneService.getAllAirplanes();

        model.addAttribute("flight", flight);
        model.addAttribute("airports", airports);
        model.addAttribute("airplanes", airplanes);
        return "AddFlight";
    }

    // Handle Form Submission for Adding a Flight
    @PostMapping("/add")
    public String addFlight(@ModelAttribute("flight") Flight flight) {
        flightService.addFlight(flight);
        return "redirect:/flights/view";
    }

    // View All Flights
    @GetMapping("/view")
    public String viewFlights(Model model) {
        List<FlightDTO> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "viewFlights";
    }
}

