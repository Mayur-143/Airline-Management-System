package com.example.AirlineManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AirlineManagementSystem.dto.FlightDetailsDTO;
import com.example.AirlineManagementSystem.model.Booking;
import com.example.AirlineManagementSystem.model.Passenger;
import com.example.AirlineManagementSystem.model.PassengerListForm;
import com.example.AirlineManagementSystem.service.AirplaneService;
import com.example.AirlineManagementSystem.service.AirportService;
import com.example.AirlineManagementSystem.service.BookingService;
import com.example.AirlineManagementSystem.service.FlightService;
import com.example.AirlineManagementSystem.service.PassengerService;

@Controller
@RequestMapping("/user/booking")
public class BookingController {

    private final BookingService bookingService;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final AirplaneService airplaneService;
    private final AirportService airportService;

    @Autowired
    public BookingController(BookingService bookingService, PassengerService passengerService, FlightService flightService, AirplaneService airplaneService, AirportService airportService) {
        this.bookingService = bookingService;
        this.passengerService = passengerService;
        this.flightService = flightService;
        this.airplaneService = airplaneService;
        this.airportService = airportService ;
    }

    @GetMapping("/passenger-details/{bookingId}")
    public String showPassengerDetailsForm(@PathVariable int bookingId, Model model) {
        model.addAttribute("bookingId", bookingId);
        PassengerListForm passengerListForm = new PassengerListForm();
        model.addAttribute("passengerListForm", passengerListForm);
        return "passengerDetails";
    }

    @PostMapping("/passenger-details/{bookingId}")
    public String addPassengers(@PathVariable int bookingId,
                                @RequestParam("totalNoPassengers") int totalNoPassengers,
                                @ModelAttribute("passengerListForm") PassengerListForm passengerListForm) {
        List<Passenger> passengers = passengerListForm.getPassengers();

        // Log the number of passengers and details for debugging
        System.out.println("Total Passengers: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("Passenger: " + passenger.getFirstName() + " " + passenger.getLastName());
        }

        if (passengers != null && !passengers.isEmpty()) {
            for (Passenger passenger : passengers) {
                passenger.setBookingId(bookingId);
                passengerService.savePassenger(passenger);
            }
            bookingService.updateTotalPassengers(bookingId, totalNoPassengers);
        }
        return "redirect:/user/booking/confirm/" + bookingId;
    }

    // Add a GetMapping for the confirm page
    @GetMapping("/confirm/{bookingId}")
    public String showConfirmBookingPage(@PathVariable int bookingId, Model model) {
        Booking booking = bookingService.findBookingById(bookingId);
        Optional<FlightDetailsDTO> optionalFlightDetails = flightService.getFlightDetailsById(booking.getFlightId());

        // Unwrap the Optional. If no flight details are found, you can return an error page or handle accordingly
        if (optionalFlightDetails.isPresent()) {
            FlightDetailsDTO flightDetails = optionalFlightDetails.get();
            List<Passenger> passengers = passengerService.findPassengersByBookingId(bookingId);
            int totalFare = calculateTotalFare(booking, passengers.size(), flightDetails);

            model.addAttribute("booking", booking);
            model.addAttribute("flightDetails", flightDetails);
            model.addAttribute("passengers", passengers);
            model.addAttribute("totalFare", totalFare);

            return "confirmBooking"; // Your Thymeleaf template name
        } else {
            // Handle the case where flight details are not found
            model.addAttribute("error", "Flight details not found.");
            return "errorPage"; // Redirect to a custom error page or handle the error accordingly
        }
    }





    @PostMapping("/confirm/{bookingId}")
    public String finalizeBooking(@PathVariable int bookingId,
                                  @RequestParam("userId") int userId) {
        List<Passenger> passengers = passengerService.getPassengersByBookingId(bookingId);
        int totalPassengers = passengers.size();
        bookingService.updateBookingDetails(bookingId, totalPassengers, userId);
        return "redirect:/user/booking/confirmation";
    }

    public int calculateTotalFare(Booking booking, int numberOfPassengers, FlightDetailsDTO flightDetails) {
        int totalFare = 0;
        String classType = booking.getClassType();
    
        // Check if flightDetails is not null
        if (flightDetails != null) {
            // Calculate fare based on the class type
            switch (classType.toLowerCase()) {
                case "economy":
                    totalFare = flightDetails.getEconomySeatFare() * numberOfPassengers;
                    break;
                case "business":
                    totalFare = flightDetails.getBusinessSeatFare() * numberOfPassengers;
                    break;
                case "firstclass":
                    totalFare = flightDetails.getFirstClassSeatFare() * numberOfPassengers;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown class type: " + classType);
            }
        } else {
            throw new IllegalArgumentException("Flight details are not available");
        }
    
        return totalFare;
    }
    
    
    
    
}
