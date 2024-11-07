package com.example.AirlineManagementSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AirlineManagementSystem.model.Contact;
import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.repository.ContactRepository;
import com.example.AirlineManagementSystem.service.UserService;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private final ContactRepository contactRepository;
    private final UserService userService; // Inject UserService

    @Autowired
    public ContactController(ContactRepository contactRepository, UserService userService) {
        this.contactRepository = contactRepository;
        this.userService = userService; // Initialize UserService
    }

    // Show the Add Contact page
    @GetMapping("/add")
    public String showAddContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "addContact";
    }

    // Save contact information
    @PostMapping("/save")
    public String saveContact(Contact contact, @AuthenticationPrincipal UserDetails userDetails) {
        // Retrieve the full User object based on the email
        Optional<User> optionalUser = userService.findUserByPrimaryEmail(userDetails.getUsername());
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            contact.setUserId(user.getUserId()); // Use the user ID from the User object
            contactRepository.save(contact);
            return "redirect:/contacts/add?success";
        } else {
            // Handle the case where the user is not found (optional)
            return "redirect:/contacts/add?error";
        }
    }
}
