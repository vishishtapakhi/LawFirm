package com.example.demo.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CommonDAO;
import com.example.demo.dao.ParalegalDAO; // Ensure you have a corresponding ParalegalDAO
import com.example.demo.model.Paralegal; // Ensure you have a corresponding Paralegal model
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;

@Controller
public class ParalegalController {

    @Autowired
    @Qualifier("customUserDetailsService") // Specify the bean to use
    private UserDetailsService userDetailsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private ParalegalDAO paralegalDAO;

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private UserServiceImpl userservice;

    @GetMapping("/register/paralegal")
    public String showParalegalForm(Model model) {
        Paralegal paralegal = new Paralegal();
        model.addAttribute("paralegal", paralegal);
        return "paralegal"; // Return the paralegal registration form view
    }

    // @PostMapping("/register/paralegal")
    // public String submitParalegalForm(@ModelAttribute("paralegal") Paralegal paralegal,
    //                                    @RequestParam String phoneNumber1,
    //                                    @RequestParam(required = false) String phoneNumber2,
    //                                    @RequestParam String email1,
    //                                    @RequestParam(required = false) String email2) {
    //     // Save the paralegal first to get the ID
    //     paralegalDAO.saveParalegal(paralegal);

    //     // After saving, retrieve the generated ID
    //     Integer paralegalId = paralegalDAO.getLastInsertId();

    //     // Save phone numbers if provided
    //     if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
    //         paralegalDAO.saveParalegalPhone(paralegalId, phoneNumber1);
    //     }
    //     if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
    //         paralegalDAO.saveParalegalPhone(paralegalId, phoneNumber2);
    //     }

    //     // Save emails if provided
    //     if (email1 != null && !email1.isEmpty()) {
    //         paralegalDAO.saveParalegalEmail(paralegalId, email1);
    //     }
    //     if (email2 != null && !email2.isEmpty()) {
    //         paralegalDAO.saveParalegalEmail(paralegalId, email2);
    //     }

    //     return "redirect:/paralegals"; // Redirect to success page
    // }
//     @PostMapping("/register/paralegal")
// public String submitParalegalForm(@ModelAttribute("paralegal") Paralegal paralegal,
//                                    @RequestParam String phoneNumber1,
//                                    @RequestParam(required = false) String phoneNumber2,
//                                    @RequestParam String email1,
//                                    @RequestParam(required = false) String email2,
//                                    Model model) {
    
//     // Check if the email already exists in the system (in any table)
//     if (commonDAO.checkEmailExistsInAllTables(email1) || 
//         (email2 != null && commonDAO.checkEmailExistsInAllTables(email2))) {
//         model.addAttribute("errorMessage", "Email already exists in the system.");
//         return "paralegal"; // Return to the registration form with an error
//     }

//     // Check if the email exists in the users table and if the role is 'role_paralegal'
//     User userWithEmail = userservice.findByEmail(email1);
//     if (userWithEmail != null && !userWithEmail.getRole().equals("ROLE_PARALEGAL")) {
//         model.addAttribute("errorMessage", "Email is already registered with a different role.");
//         return "paralegal"; // Return to the registration form with an error
//     }

//     // Check for existing phone numbers
//     if (commonDAO.checkPhoneExistsInAllTables(phoneNumber1) || 
//         (phoneNumber2 != null && commonDAO.checkPhoneExistsInAllTables(phoneNumber2))) {
//         model.addAttribute("errorMessage", "Phone number already exists in the system.");
//         return "paralegal"; // Return to the registration form with an error
//     }

//     // Save the paralegal to the database
//     paralegalDAO.saveParalegal(paralegal);
//     Integer paralegalId = paralegalDAO.getLastInsertId();

//     // Save phone numbers if provided
//     if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
//         paralegalDAO.saveParalegalPhone(paralegalId, phoneNumber1);
//     }
//     if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
//         paralegalDAO.saveParalegalPhone(paralegalId, phoneNumber2);
//     }

//     // Save emails if provided
//     if (email1 != null && !email1.isEmpty()) {
//         paralegalDAO.saveParalegalEmail(paralegalId, email1);
//     }
//     if (email2 != null && !email2.isEmpty()) {
//         paralegalDAO.saveParalegalEmail(paralegalId, email2);
//     }

//     return "redirect:/paralegals"; // Redirect to success page
// }
@PostMapping("/register/paralegal")
public String submitParalegalForm(@ModelAttribute("paralegal") Paralegal paralegal,
                                   @RequestParam String phoneNumber1,
                                   @RequestParam(required = false) String phoneNumber2,
                                   @RequestParam String email1,
                                   @RequestParam(required = false) String email2,
                                   Model model) {
    
    // Check if the email already exists in the system (in any table)
    if (commonDAO.checkEmailExistsInAllTables(email1) || 
        (email2 != null && commonDAO.checkEmailExistsInAllTables(email2))) {

        // Check if the email exists in the users table and if the role is 'ROLE_PARALEGAL'
        User userWithEmail1 = userservice.findByEmail(email1);
        User userWithEmail2 = email2 != null ? userservice.findByEmail(email2) : null;

        if ((userWithEmail1 != null && !userWithEmail1.getRole().equals("ROLE_PARALEGAL")) ||
            (userWithEmail2 != null && !userWithEmail2.getRole().equals("ROLE_PARALEGAL"))) {
            model.addAttribute("errorMessage", "Email is already registered with a different role.");
            return "paralegal"; // Return to the registration form with an error
        }

        model.addAttribute("errorMessage", "Email already exists in the system.");
        return "paralegal"; // Return to the registration form with an error
    }

    // Check for existing phone numbers
    if (commonDAO.checkPhoneExistsInAllTables(phoneNumber1) || 
        (phoneNumber2 != null && commonDAO.checkPhoneExistsInAllTables(phoneNumber2))) {
        model.addAttribute("errorMessage", "Phone number already exists in the system.");
        return "paralegal"; // Return to the registration form with an error
    }

    // Save the paralegal to the database
    paralegalDAO.saveParalegal(paralegal);
    Integer paralegalId = paralegalDAO.getLastInsertId();

    // Save phone numbers if provided
    if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
        paralegalDAO.saveParalegalPhone(paralegalId, phoneNumber1);
    }
    if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
        paralegalDAO.saveParalegalPhone(paralegalId, phoneNumber2);
    }

    // Save emails if provided
    if (email1 != null && !email1.isEmpty()) {
        paralegalDAO.saveParalegalEmail(paralegalId, email1);
    }
    if (email2 != null && !email2.isEmpty()) {
        paralegalDAO.saveParalegalEmail(paralegalId, email2);
    }

    return "redirect:/paralegals"; // Redirect to success page
}


    @GetMapping("/paralegals")
    public String listParalegals(Model model) {
        List<Paralegal> paralegals = paralegalDAO.listParalegals();
        model.addAttribute("paralegals", paralegals);
        return "para_list"; // Return a view name for displaying the paralegal list
    }

    @GetMapping("/paralegals/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            // Fetch the paralegal by ID
            Paralegal paralegal = paralegalDAO.getParalegalById(id);
            model.addAttribute("paralegal", paralegal);

            // Fetch associated phone numbers and emails
            List<String> paralegalPhones = paralegalDAO.getParalegalPhones(id); // List of phone numbers as Strings
            List<String> paralegalEmails = paralegalDAO.getParalegalEmails(id); // List of ParalegalEmail objects
            
            model.addAttribute("paralegalPhones", paralegalPhones);
            model.addAttribute("paralegalEmails", paralegalEmails);

            // Safely retrieve phone numbers and emails
            String phoneNumber1 = paralegalPhones.size() > 0 ? paralegalPhones.get(0) : ""; // Correctly get the phone number
            String phoneNumber2 = paralegalPhones.size() > 1 ? paralegalPhones.get(1) : ""; // Correctly get the second phone number
            String email1 = paralegalEmails.size() > 0 ? paralegalEmails.get(0) : ""; // Correctly get the first email
            String email2 = paralegalEmails.size() > 1 ? paralegalEmails.get(1) : ""; // Correctly get the second email

            // Add the phone numbers and emails to the model
            model.addAttribute("phoneNumber1", phoneNumber1);
            model.addAttribute("phoneNumber2", phoneNumber2);
            model.addAttribute("email1", email1);
            model.addAttribute("email2", email2);
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("error", "Paralegal not found");
            return "error_page"; // Redirect to an error page or handle accordingly
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while retrieving the paralegal data");
            return "error_page"; // Redirect to an error page for other exceptions
        }
        
        return "edit_paralegal"; // Render the edit paralegal form
    }

    @PostMapping("/paralegals/update")
    public String updateParalegal(@ModelAttribute Paralegal paralegal, 
                                   @RequestParam String phoneNumber1, 
                                   @RequestParam String phoneNumber2, 
                                   @RequestParam String email1,
                                   @RequestParam String email2) {
        // Update the paralegal information
        paralegalDAO.updateParalegal(paralegal);

        // Update phone numbers and emails
        List<String> newPhoneNumbers = new ArrayList<>();
        if (!phoneNumber1.isEmpty()) newPhoneNumbers.add(phoneNumber1);
        if (!phoneNumber2.isEmpty()) newPhoneNumbers.add(phoneNumber2);
        paralegalDAO.updateParalegalPhones(paralegal.getParalegalID(), newPhoneNumbers);

        List<String> newEmails = new ArrayList<>();
        if (!email1.isEmpty()) newEmails.add(email1);
        if (!email2.isEmpty()) newEmails.add(email2);
        paralegalDAO.updateParalegalEmails(paralegal.getParalegalID(), newEmails);

        return "redirect:/paralegals"; // Redirect to the paralegal list after updating
    }

    @PostMapping("/paralegals/delete/{id}")
    public String deleteParalegal(@PathVariable("id") Integer id) {
        paralegalDAO.deleteParalegal(id);
        paralegalDAO.deleteParalegalPhone(id); // Delete associated phone numbers
        paralegalDAO.deleteParalegalEmail(id); // Delete associated emails
        return "redirect:/paralegals"; // Redirect to the paralegal list after deletion
    }

    @GetMapping("/paralegals/search")
    public String searchParalegals(@RequestParam("query") String query, Model model) {
        List<Paralegal> paralegals = paralegalDAO.searchParalegals(query);
        model.addAttribute("paralegals", paralegals);
        model.addAttribute("searchQuery", query); // Add the search query to the model to show it on the view
        return "para_list"; // Return the view name for displaying the paralegal list
    }

    @GetMapping("/paradashboard")
    public String getParaDashboard(Model model, Principal principal) {
        // Get the logged-in user's email
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        String loggedInEmail = userDetails.getUsername(); // Assuming username is the email
    
        // Check if the user has the 'LAWYER' role
        if (userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_PARALEGAL"))) {
            // Fetch lawyer details from the database using the logged-in email
            List<Paralegal> paralegals = paralegalDAO.getParalegalByEmail(loggedInEmail);
    
            if (!paralegals.isEmpty()) {
                // Lawyer found, set status to "Approved"
                Paralegal paralegal = paralegals.get(0); // Get the first lawyer
                model.addAttribute("status", "Approved");
                model.addAttribute("paralegalId", paralegal.getParalegalID());
                // SQL query to fetch tasks assigned to the lawyer (EmpID is lawyer.getLawyerID())
                String sql = "SELECT t.TaskID, t.TaskDesc, t.Status, t.CaseID, t.CatID, c.CaseType, " +
                         "COALESCE(corporate.CaseDesc, matrimonial.CaseDesc, civil.CaseDesc, criminal.CaseDesc) AS CaseDesc " +
                         "FROM Taskparassigned ta " +
                         "JOIN Task t ON ta.TaskID = t.TaskID " +
                         "JOIN Category c ON t.CatID = c.CatID " +
                         "LEFT JOIN CorporateCase corporate ON t.CaseID = corporate.CorporateCaseID AND c.CaseType = 'Corporate' " +
                         "LEFT JOIN MatrimonialCase matrimonial ON t.CaseID = matrimonial.MatrimonialCaseID AND c.CaseType = 'Matrimonial' " +
                         "LEFT JOIN CivilCase civil ON t.CaseID = civil.CivilCaseID AND c.CaseType = 'Civil' " +
                         "LEFT JOIN CriminalCase criminal ON t.CaseID = criminal.CriminalCaseID AND c.CaseType = 'Criminal' " +
                         "WHERE ta.EmpID = ?";
    
                // Fetch tasks and cases assigned to the lawyer
                List<Map<String, Object>> tasksAssigned = jdbcTemplate.queryForList(sql, paralegal.getParalegalID());
        
                if (!tasksAssigned.isEmpty() ) {
                    // Group tasks by case type
                    Map<String, List<Map<String, Object>>> tasksByCaseType = tasksAssigned.stream()
                            .collect(Collectors.groupingBy(task -> (String) task.get("CaseType")));
    
                    model.addAttribute("tasksByCaseType", tasksByCaseType);
                
                    model.addAttribute("assignedTasksCount", tasksAssigned.size());
                } else {
                    // No tasks or cases assigned
                    model.addAttribute("noAssignments", "No cases or tasks assigned.");
                }
            } else {
                // Lawyer not found, status is "Not Approved"
                model.addAttribute("status", "Not Approved");
            }
        } else {
            // User does not have the 'LAWYER' role
            model.addAttribute("error", "Access Denied: You are not authorized to view this page.");
        }
       
        return "paralegaldash"; // Thymeleaf template for the lawyer dashboard
    }
}
