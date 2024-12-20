package com.example.AirlineManagementSystem.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import com.example.AirlineManagementSystem.dto.FlightDTO;
import com.example.AirlineManagementSystem.dto.FlightDetailsDTO;
import com.example.AirlineManagementSystem.model.Airplane;
import com.example.AirlineManagementSystem.model.Airport;
import com.example.AirlineManagementSystem.model.Flight;
import com.example.AirlineManagementSystem.service.AirplaneService;
import com.example.AirlineManagementSystem.service.AirportService;
import com.example.AirlineManagementSystem.service.BookingService;
import com.example.AirlineManagementSystem.service.FlightService;
import com.example.AirlineManagementSystem.service.FlightServiceInterface;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class FlightBookingController {
    
    private final FlightServiceInterface flightService;
    private final AirportService airportService;
    private final AirplaneService airplaneService;
    private BookingService bookingService;


    public FlightBookingController(FlightServiceInterface flightService, AirportService airportService, AirplaneService airplaneService, BookingService bookingService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.airplaneService = airplaneService;
        this.bookingService = bookingService; 
    }
    
    @GetMapping("/booking")
    public String viewFlights(Model model) {
        // Show all flights by default
        List<FlightDTO> flights = flightService.getAllFlights();
        Flight flight = new Flight();
        List<Airport> airports = airportService.getAllAirports();
        List<Airplane> airplanes = airplaneService.getAllAirplanes();

        model.addAttribute("flight", flight);
        model.addAttribute("airports", airports);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("flights", flights); // Show all flights
        return "flightBooking";
    }

    @PostMapping("/booking")
    public String searchFlights(
        @ModelAttribute("flight") Flight flight,
        @RequestParam int departureAirportId,
        @RequestParam int arrivalAirportId,
        @RequestParam(required = false) LocalDateTime startDate,
        Model model
    ) {
        // Fetch flights based on search criteria
        List<FlightDTO> flights = flightService.searchFlights(departureAirportId, arrivalAirportId, startDate);
        
        if (flights.isEmpty()) {
            model.addAttribute("message", "No flights available for the selected criteria.");
        }

        List<Airport> airports = airportService.getAllAirports();
        List<Airplane> airplanes = airplaneService.getAllAirplanes();
        model.addAttribute("flight", flight);
        model.addAttribute("airports", airports);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("flights", flights);
        return "flightBooking";
    }

    
    @GetMapping("/booking/details/{flightId}")
    public String getFlightDetails(@PathVariable("flightId") int flightId, Model model) {
        // Fetch FlightDetailsDTO directly, including all flight and airplane details
        Optional<FlightDetailsDTO> flightDetailsOpt = flightService.getFlightDetailsById(flightId);

        if (flightDetailsOpt.isPresent()) {
            model.addAttribute("flightDetails", flightDetailsOpt.get());
        } else {
            model.addAttribute("message", "Flight not found.");
        }

        return "flightDetails";  // View for displaying flight details
    }

    @PostMapping("/booking/details/{flightId}")
    public String createBooking(@PathVariable int flightId, @RequestParam("bookingClass") String classType) {
        int bookingId =bookingService.createBooking(flightId, classType);
        return "redirect:/user/booking/passenger-details/" + bookingId; // Pass bookingId here
    }

}
