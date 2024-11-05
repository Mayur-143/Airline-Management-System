package com.example.AirlineManagementSystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AirlineManagementSystem.model.Booking;
import com.example.AirlineManagementSystem.model.CustomUserDetails;
import com.example.AirlineManagementSystem.model.PaymentDetails;
import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.service.BookingService;
import com.example.AirlineManagementSystem.service.PaymentDetailsService;
import com.example.AirlineManagementSystem.service.UserService;

@Controller
@RequestMapping("/user/booking/payment")
public class PaymentDetailsController {

    private final PaymentDetailsService paymentDetailsService;
    private final BookingService bookingService;
    private final UserService userService;

    @Autowired
    public PaymentDetailsController(PaymentDetailsService paymentDetailsService, BookingService bookingService, UserService userService) {
        this.paymentDetailsService = paymentDetailsService;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @GetMapping("/{bookingId}")
public String showPaymentPage(@PathVariable int bookingId, 
                               @RequestParam("totalFare") int totalFare, 
                               Model model) {
    // Fetch the booking details using the bookingId
    Booking booking = bookingService.findBookingById(bookingId);
    
    // Retrieve the authentication object to get the current user
    org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    // Extract the username from the authentication object
    String username = authentication.getName(); // Get the logged-in user's email or username
    
    // Fetch the user by username
    Optional<User> optionalUser = userService.findUserByPrimaryEmail(username);
    
    if (optionalUser.isPresent()) {
        User user = optionalUser.get(); // Get the user from the Optional
        int userId = user.getUserId(); // Retrieve the userId
        
        // Add attributes to the model for the view
        model.addAttribute("bookingId", bookingId);
        model.addAttribute("userId", userId);
        model.addAttribute("totalFare", totalFare);
        
        return "paymentPage"; // Return the payment page template
    } else {
        // Handle the case where the user is not found
        model.addAttribute("error", "User not found.");
        return "errorPage"; // Redirect to an error page if the user is not found
    }
}

    
@PostMapping("/process/{bookingId}")
public String processPayment(@PathVariable int bookingId,
                             @RequestParam("userId") int userId,
                             @RequestParam("cardNumber") int cardNumber,
                             @RequestParam("cardHolderName") String cardHolderName,
                             @RequestParam("cvv") int cvv,
                             @RequestParam("expiryDate") String expiryDateStr,
                             @RequestParam("totalFare") int totalFare) {  // Added totalFare parameter
    try {
        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        dateFormat.setLenient(false);

        // Convert expiryDateStr to Date
        Date expiryDate = dateFormat.parse(expiryDateStr);

        // Create and populate PaymentDetails object
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setBookingId(bookingId);
        paymentDetails.setCardNumber(cardNumber);
        paymentDetails.setCardHolderName(cardHolderName);
        paymentDetails.setCvv(cvv);
        paymentDetails.setExpiryDate(expiryDate);
        paymentDetails.setAmount(totalFare); // Use totalFare directly

        // Save payment details and update booking status
        paymentDetailsService.savePaymentDetails(paymentDetails);
        bookingService.updateBookingStatus(bookingId, "Confirmed");

        return "redirect:/user/booking/confirmation/" + bookingId;
        
    } catch (ParseException e) {
        // Handle invalid date format or other input errors
        return "errorPage"; // Redirect to a custom error page
    }
}


}

