package com.example.demo.Controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ClientDAO;
import com.example.demo.dao.CommonDAO;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;



@Controller
public class ClientController {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/register")
    public String showForm(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "register_form";
    }

    // @PostMapping("/register")
    // public String submitForm(@ModelAttribute("client") Client client,
    //                          @RequestParam String phoneNumber1,
    //                          @RequestParam(required = false) String phoneNumber2,
    //                          @RequestParam String email1,
    //                          @RequestParam(required = false) String email2) {
    //     // Save the client first to get the ID
    //     clientDAO.saveClient(client);

    //     // After saving, retrieve the generated ID
    //     Integer clientId = clientDAO.getLastInsertId();

    //     // Save phone numbers if provided
    //     if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
    //         clientDAO.saveClientPhone(clientId, phoneNumber1);
    //     }
    //     if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
    //         clientDAO.saveClientPhone(clientId, phoneNumber2);
    //     }

    //     // Save emails if provided
    //     if (email1 != null && !email1.isEmpty()) {
    //         clientDAO.saveClientEmail(clientId, email1);
    //     }
    //     if (email2 != null && !email2.isEmpty()) {
    //         clientDAO.saveClientEmail(clientId, email2);
    //     }

    //     return "redirect:/clients"; // Redirect to success page
    // }
//     @PostMapping("/register")
// public String submitForm(@ModelAttribute("client") Client client,
//                          @RequestParam String phoneNumber1,
//                          @RequestParam(required = false) String phoneNumber2,
//                          @RequestParam String email1,
//                          @RequestParam(required = false) String email2,
//                          Model model) {
    
//     // Check if the email already exists in the system (in any table)
//     if (commonDAO.checkEmailExistsInAllTables(email1) || 
//         (email2 != null && commonDAO.checkEmailExistsInAllTables(email2))) {
//         model.addAttribute("errorMessage", "Email already exists in the system.");
//         return "register_form"; // Return to the registration form with an error
//     }

//     // Check if the email exists in the users table and if the role is 'role_client'
//     User userWithEmail = userService.findByEmail(email1);
//     if (userWithEmail != null && !userWithEmail.getRole().equals("ROLE_CLIENT")) {
//         model.addAttribute("errorMessage", "Email is already registered with a different role.");
//         return "register_form"; // Return to the registration form with an error
//     }

//     // Check for existing phone numbers
//     if (commonDAO.checkPhoneExistsInAllTables(phoneNumber1) || 
//         (phoneNumber2 != null && commonDAO.checkPhoneExistsInAllTables(phoneNumber2))) {
//         model.addAttribute("errorMessage", "Phone number already exists in the system.");
//         return "register_form"; // Return to the registration form with an error
//     }

//     // Save the client to the database
//     clientDAO.saveClient(client);
//     Integer clientId = clientDAO.getLastInsertId();

//     // Save phone numbers if provided
//     if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
//         clientDAO.saveClientPhone(clientId, phoneNumber1);
//     }
//     if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
//         clientDAO.saveClientPhone(clientId, phoneNumber2);
//     }

//     // Save emails if provided
//     if (email1 != null && !email1.isEmpty()) {
//         clientDAO.saveClientEmail(clientId, email1);
//     }
//     if (email2 != null && !email2.isEmpty()) {
//         clientDAO.saveClientEmail(clientId, email2);
//     }

//     return "redirect:/clients"; // Redirect to success page
// }

@PostMapping("/register")
public String submitClientForm(@ModelAttribute("client") Client client,
                               @RequestParam String phoneNumber1,
                               @RequestParam(required = false) String phoneNumber2,
                               @RequestParam String email1,
                               @RequestParam(required = false) String email2,
                               Model model) {

    // Check if the email already exists in the system (in any table)
    if (commonDAO.checkEmailExistsInAllTables(email1) || 
        (email2 != null && commonDAO.checkEmailExistsInAllTables(email2))) {

        // Check if the email exists in the users table and if the role is 'ROLE_CLIENT'
        User userWithEmail1 = userService.findByEmail(email1);
        User userWithEmail2 = email2 != null ? userService.findByEmail(email2) : null;

        if ((userWithEmail1 != null && !userWithEmail1.getRole().equals("ROLE_CLIENT")) ||
            (userWithEmail2 != null && !userWithEmail2.getRole().equals("ROLE_CLIENT"))) {
            model.addAttribute("errorMessage", "Email is already registered with a different role.");
            return "register_form"; // Return to the registration form with an error
        }

        model.addAttribute("errorMessage", "Email already exists in the system.");
        return "register_form"; // Return to the registration form with an error
    }

    // Check for existing phone numbers
    if (commonDAO.checkPhoneExistsInAllTables(phoneNumber1) || 
        (phoneNumber2 != null && commonDAO.checkPhoneExistsInAllTables(phoneNumber2))) {
        model.addAttribute("errorMessage", "Phone number already exists in the system.");
        return "register_form"; // Return to the registration form with an error
    }

    // Save the client to the database
    clientDAO.saveClient(client);
    Integer clientId = clientDAO.getLastInsertId();

    // Save phone numbers if provided
    if (phoneNumber1 != null && !phoneNumber1.isEmpty()) {
        clientDAO.saveClientPhone(clientId, phoneNumber1);
    }
    if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
        clientDAO.saveClientPhone(clientId, phoneNumber2);
    }

    // Save emails if provided
    if (email1 != null && !email1.isEmpty()) {
        clientDAO.saveClientEmail(clientId, email1);
    }
    if (email2 != null && !email2.isEmpty()) {
        clientDAO.saveClientEmail(clientId, email2);
    }

    return "redirect:/clients"; // Redirect to the clients page after successful registration
}




    @GetMapping("/clients")
    public String listClients(Model model) {
        List<Client> clients = clientDAO.listClients();
        model.addAttribute("clients", clients);
        return "client_list"; // Return a view name for displaying the client list
    }


    @GetMapping("/clients/edit/{id}")
public String showEditForm(@PathVariable("id") Integer id, Model model) {
    try {
        // Fetch the client by ID
        Client client = clientDAO.getClientById(id);
        model.addAttribute("client", client);

        // Fetch associated phone numbers and emails
        List<String> clientPhones = clientDAO.getClientPhones(id); // List of phone numbers as Strings
        List<String> clientEmails = clientDAO.getClientEmails(id); // List of ClientEmail objects
        
        model.addAttribute("clientPhones", clientPhones);
        model.addAttribute("clientEmails", clientEmails);

        // Safely retrieve phone numbers and emails
        String phoneNumber1 = clientPhones.size() > 0 ? clientPhones.get(0) : ""; // Correctly get the phone number
        String phoneNumber2 = clientPhones.size() > 1 ? clientPhones.get(1) : ""; // Correctly get the second phone number
        String email1 = clientEmails.size() > 0 ? clientEmails.get(0) : ""; // Correctly get the first email
        String email2 = clientEmails.size() > 1 ? clientEmails.get(1) : ""; // Correctly get the second email


        // Add the phone numbers and emails to the model
        model.addAttribute("phoneNumber1", phoneNumber1);
        model.addAttribute("phoneNumber2", phoneNumber2);
        model.addAttribute("email1", email1);
        model.addAttribute("email2", email2);
    } catch (EmptyResultDataAccessException e) {
        model.addAttribute("error", "Client not found");
        return "error_page"; // Redirect to an error page or handle accordingly
    } catch (Exception e) {
        model.addAttribute("error", "An error occurred while retrieving the client data");
        return "error_page"; // Redirect to an error page for other exceptions
    }
    
    return "edit_client"; // Render the edit client form
}

@PostMapping("/clients/update")
public String updateClient(@ModelAttribute Client client, @RequestParam String phoneNumber1, 
                           @RequestParam String phoneNumber2, @RequestParam String email1,
                           @RequestParam String email2, Model model) {

    // Check if the email already exists in the client table (excluding current client)
    if (commonDAO.checkEmailExistsInClientTableExcludingId(email1, client.getClientId()) || 
        (email2 != null && commonDAO.checkEmailExistsInClientTableExcludingId(email2, client.getClientId()))) {

        model.addAttribute("errorMessage", "Email already exists in the system for another client.");
        return "edit_client"; // Return to the edit form with an error
    }

    // Check if the email already exists in the users table
    if (commonDAO.checkEmailExistsInUsersTable(email1) || 
        (email2 != null && commonDAO.checkEmailExistsInUsersTable(email2))) {

        model.addAttribute("errorMessage", "Email already exists in the system for another user.");
        return "edit_client"; // Return to the edit form with an error
    }

    // Check if the email exists in the lawyer or paralegal tables
    if (commonDAO.checkEmailExistsInLawyerTable(email1) || 
        (email2 != null && commonDAO.checkEmailExistsInLawyerTable(email2)) ||
        commonDAO.checkEmailExistsInParalegalTable(email1) ||
        (email2 != null && commonDAO.checkEmailExistsInParalegalTable(email2))) {

        model.addAttribute("errorMessage", "Email already exists in the system for another professional (Lawyer/Paralegal).");
        return "edit_client"; // Return to the edit form with an error
    }

    // Check for existing phone numbers in client, lawyer, and paralegal tables
    if (commonDAO.checkPhoneExistsInClientTableExcludingId(phoneNumber1, client.getClientId()) || 
        (phoneNumber2 != null && commonDAO.checkPhoneExistsInClientTableExcludingId(phoneNumber2, client.getClientId())) ||
        commonDAO.checkPhoneExistsInLawyerTable(phoneNumber1) || 
        (phoneNumber2 != null && commonDAO.checkPhoneExistsInLawyerTable(phoneNumber2)) ||
        commonDAO.checkPhoneExistsInParalegalTable(phoneNumber1) ||
        (phoneNumber2 != null && commonDAO.checkPhoneExistsInParalegalTable(phoneNumber2))) {

        model.addAttribute("errorMessage", "Phone number already exists in the system.");
        return "edit_client"; // Return to the edit form with an error
    }

    // Update the client information
    clientDAO.updateClient(client);

    // Update phone numbers and emails
    List<String> newPhoneNumbers = new ArrayList<>();
    if (!phoneNumber1.isEmpty()) newPhoneNumbers.add(phoneNumber1);
    if (!phoneNumber2.isEmpty()) newPhoneNumbers.add(phoneNumber2);
    clientDAO.updateClientPhones(client.getClientId(), newPhoneNumbers);

    List<String> newEmails = new ArrayList<>();
    if (!email1.isEmpty()) newEmails.add(email1);
    if (!email2.isEmpty()) newEmails.add(email2);
    clientDAO.updateClientEmails(client.getClientId(), newEmails);

    return "redirect:/clients"; // Redirect to the client list after updating
}


//     @PostMapping("/clients/update")
// public String updateClient(@ModelAttribute Client client, @RequestParam String phoneNumber1, 
//                            @RequestParam String phoneNumber2, @RequestParam String email1,
//                            @RequestParam String email2) {
//     // Update the client information
//     clientDAO.updateClient(client);

//     // Update phone numbers and emails
//     List<String> newPhoneNumbers = new ArrayList<>();
//     if (!phoneNumber1.isEmpty()) newPhoneNumbers.add(phoneNumber1);
//     if (!phoneNumber2.isEmpty()) newPhoneNumbers.add(phoneNumber2);
//     clientDAO.updateClientPhones(client.getClientId(), newPhoneNumbers);

//     List<String> newEmails = new ArrayList<>();
//     if (!email1.isEmpty()) newEmails.add(email1);
//     if (!email2.isEmpty()) newEmails.add(email2);
//     clientDAO.updateClientEmails(client.getClientId(), newEmails);

//     return "redirect:/clients"; // Redirect to the client list after updating
// }


    @PostMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable("id") Integer id) {
        clientDAO.deleteClient(id);
        clientDAO.deleteClientPhone(id); // Delete associated phone numbers
        clientDAO.deleteClientEmail(id); // Delete associated emails
        return "redirect:/clients"; // Redirect to the client list after deletion
    }


    @GetMapping("/clients/search")
public String searchClients(@RequestParam("query") String query, Model model) {
    List<Client> clients = clientDAO.searchClients(query);
    model.addAttribute("clients", clients);
    model.addAttribute("searchQuery", query); // Add the search query to the model to show it on the view
    return "client_list"; // Return the view name for displaying the client list
}

}
