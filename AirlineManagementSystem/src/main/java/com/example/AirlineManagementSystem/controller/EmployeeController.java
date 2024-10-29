// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;

// import com.example.AirlineManagementSystem.model.Employee;
// import com.example.AirlineManagementSystem.service.EmployeeService;

// import org.springframework.web.bind.annotation.ModelAttribute;

// import java.util.List;

// @Controller
// public class EmployeeController {

//     @Autowired
//     private EmployeeService employeeService;

//     @Autowired
//     private AirportService airportService; // Assuming you have this service for airport fetching

//     @GetMapping("/employees/register")
//     public String showRegistrationForm(Model model) {
//         model.addAttribute("employee", new Employee());
//         List<Airport> airports = airportService.findAll(); // Fetching airports for dropdown
//         model.addAttribute("airports", airports);
//         return "admin/register_employee"; // Thymeleaf template for registration
//     }

//     @PostMapping("/employees/register") // Handle the form submission
//     public String registerEmployee(@ModelAttribute Employee employee, Model model) {
//         employeeService.saveEmployee(employee); // Save employee to the database
//         model.addAttribute("successMessage", "Employee registered successfully!");
//         return "redirect:/employees"; // Redirect to a suitable page after registration
//     }
// }
