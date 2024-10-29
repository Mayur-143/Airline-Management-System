package com.example.AirlineManagementSystem.controller;

import com.example.AirlineManagementSystem.dto.UserLoginDto;
import com.example.AirlineManagementSystem.model.User;
import com.example.AirlineManagementSystem.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
 //@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Autowire the password encoder

    // To show the home page
    @GetMapping("user/home")
    public String showHomePage() {
        return "home"; // This will render the home.html template
    }

    @GetMapping("admin/home")
    public String showAdminHomePage() {
        return "adminHome"; // This will render the home.html template
    }

    // Show the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This will render login.html template
    }

    // To show the registration page
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User()); // This adds an empty user object for the form
        return "register"; // This will render the register.html template
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam String primaryEmail, Model model) {
        // System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Check if email already exists
        if (userService.primaryEmailExists(primaryEmail)) {
            model.addAttribute("error", "Primary email already exists. Please use a different email.");
            return "register"; // Stay on the registration page
        }

        // Set the primary email and register the user
        user.setPrimaryEmail(primaryEmail);
        userService.addUser(user); // This will save the new user in the database
        System.out.println(user);
        return "redirect:/user/home"; // Redirect to home after successful registration
    }

//     @PostMapping("/login")
//     public String login(@RequestParam String primaryEmail, @RequestParam String rawPassword) {
//     System.out.println("Attempting to log in with email: " + primaryEmail);
//     System.out.println("Raw Password Entered: " + rawPassword);
    
//     Optional<User> optionaluser = userService.findUserByPrimaryEmail(primaryEmail);
    
//     if(optionaluser.isPresent()){
//         User user = optionaluser.get(); // Retrieve the User from Optional
        
//         // Check if the provided password matches the stored password hash
//         boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
//         System.out.println("Password Match: " + matches);
        
//         if (matches) {
//             // Successful login logic
//             return "redirect:/home"; // Example redirect
//         } else {
//             // Invalid password logic
//             return "redirect:/login?error=true";
//         }
//     }
    
//     return "redirect:/login?error=true"; // User not found
// }

    
    
}
