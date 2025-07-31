package com.example.AirlineManagementSystem.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.AirlineManagementSystem.dto.FlightDetailsDTO;
import com.example.AirlineManagementSystem.model.Booking;
import com.example.AirlineManagementSystem.model.Passenger;
import com.example.AirlineManagementSystem.model.PassengerListForm;
import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.service.AirplaneService;
import com.example.AirlineManagementSystem.service.AirportService;
import com.example.AirlineManagementSystem.service.BookingService;
import com.example.AirlineManagementSystem.service.FlightService;
import com.example.AirlineManagementSystem.service.PassengerService;
import com.example.AirlineManagementSystem.service.UserService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import jakarta.servlet.http.HttpServletResponse;

import com.example.AirlineManagementSystem.dto.BookingDetailsDTO;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.AirlineManagementSystem.model.User;

@Controller
@RequestMapping("/user/booking")
public class BookingController {

    private final BookingService bookingService;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final AirplaneService airplaneService;
    private final AirportService airportService;
    private final UserService userService;

    @Autowired
    public BookingController(BookingService bookingService, PassengerService passengerService, FlightService flightService, AirplaneService airplaneService, AirportService airportService, UserService userService) {
        this.bookingService = bookingService;
        this.passengerService = passengerService;
        this.flightService = flightService;
        this.airplaneService = airplaneService;
        this.airportService = airportService ;
        this.userService = userService ;
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

    @GetMapping("/view_bookings")
    public String viewBookings(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Optional<User> optionalUser = userService.findUserByPrimaryEmail(userDetails.getUsername());
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            int userId = foundUser.getUserId();
            List<BookingDetailsDTO> bookingDetails = bookingService.getBookingsByUserId(userId);
            model.addAttribute("bookingDetails", bookingDetails);
            return "viewBookings";
        } else {
            // Handle the case where the user is not found (optional)
            return "redirect:/contacts/add?error";
        }
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



    @GetMapping("/confirmation/{bookingId}")
    public String finalizeBooking(@PathVariable int bookingId) {
        
        return "confirmation";
    }

    @GetMapping("/download-ticket/{bookingId}")
    public void downloadTicket(@PathVariable int bookingId, HttpServletResponse response) throws IOException {
        // Set up the HTTP response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ticket_" + bookingId + ".pdf");

        // Fetch the booking details
        List<BookingDetailsDTO> bookingDetailsList = bookingService.getBookingDetailsById(bookingId);

        if (!bookingDetailsList.isEmpty()) {
            try {
                // Create a PDF document
                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();

                // Set fonts for the document
                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
                Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

                // Add ticket title
                Paragraph title = new Paragraph("Flight Ticket Details", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20); // Add space after title
                document.add(title);

                // Create a table for common booking and flight details
                PdfPTable bookingTable = new PdfPTable(2); // 2 columns
                bookingTable.setWidthPercentage(100); // Full width

                // Add table headers
                bookingTable.addCell(new PdfPCell(new Phrase("Booking Information", headerFont)));
                bookingTable.addCell(new PdfPCell(new Phrase(""))); // Empty second cell for the title row

                // Add booking details
                BookingDetailsDTO firstBooking = bookingDetailsList.get(0); // Use the first item for common details
                bookingTable.addCell(new Phrase("Booking ID:", regularFont));
                bookingTable.addCell(new Phrase(String.valueOf(firstBooking.getBookingId()), regularFont));
                bookingTable.addCell(new Phrase("Booking Date:", regularFont));
                bookingTable.addCell(new Phrase(firstBooking.getBookingDateTime().toString(), regularFont));
                bookingTable.addCell(new Phrase("Flight Number:", regularFont));
                bookingTable.addCell(new Phrase(firstBooking.getFlightNumber(), regularFont));
                bookingTable.addCell(new Phrase("Departure Time:", regularFont));
                bookingTable.addCell(new Phrase(firstBooking.getDepartureTime().toString(), regularFont));
                bookingTable.addCell(new Phrase("Arrival Time:", regularFont));
                bookingTable.addCell(new Phrase(firstBooking.getArrivalTime().toString(), regularFont));
                bookingTable.addCell(new Phrase("Departure Airport:", regularFont));
                bookingTable.addCell(new Phrase(firstBooking.getDepartureAirport(), regularFont));
                bookingTable.addCell(new Phrase("Arrival Airport:", regularFont));
                bookingTable.addCell(new Phrase(firstBooking.getArrivalAirport(), regularFont));
                bookingTable.addCell(new Phrase("Class:", regularFont));
                bookingTable.addCell(new Phrase(firstBooking.getFlightClass(), regularFont));
                bookingTable.addCell(new Phrase("Seat Fare:", regularFont));
                bookingTable.addCell(new Phrase("₹" + firstBooking.getSeatFare(), regularFont));

                document.add(bookingTable); // Add the table to the document
                document.add(Chunk.NEWLINE); // Add space after the table

                // Add a section for passenger-specific details
                Paragraph passengerHeader = new Paragraph("Passenger Details", headerFont);
                passengerHeader.setSpacingBefore(10); // Add space before passenger section
                passengerHeader.setSpacingAfter(10);  // Add space after header
                document.add(passengerHeader);

                // Create a table for passenger details
                PdfPTable passengerTable = new PdfPTable(3); // 3 columns for passenger info
                passengerTable.setWidthPercentage(100);

                // Add table headers for passenger info
                passengerTable.addCell(new PdfPCell(new Phrase("Passenger Name", headerFont)));
                passengerTable.addCell(new PdfPCell(new Phrase("Age", headerFont)));
                passengerTable.addCell(new PdfPCell(new Phrase("Gender", headerFont)));

                // Add passenger-specific details
                for (BookingDetailsDTO booking : bookingDetailsList) {
                    passengerTable.addCell(new PdfPCell(new Phrase(booking.getPassengerName(), regularFont)));
                    passengerTable.addCell(new PdfPCell(new Phrase(String.valueOf(booking.getAge()), regularFont)));
                    passengerTable.addCell(new PdfPCell(new Phrase(booking.getGender(), regularFont)));
                }

                document.add(passengerTable); // Add passenger table to the document

                // Close the document
                document.close();
            } catch (DocumentException e) {
                throw new IOException("Error generating PDF", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found");
        }
    }



    @GetMapping("/cancel-ticket/{bookingId}")
    public String cancelTicket(@PathVariable int bookingId, RedirectAttributes redirectAttributes) {
        try {
            String status = "Cancelled";
            bookingService.updateBookingStatus(bookingId, status);
            redirectAttributes.addFlashAttribute("message", "Ticket cancelled successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel the ticket.");
        }
        return "redirect:/user/booking/view_bookings"; // Redirect to the bookings page after cancellation
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
                case "first class":
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
