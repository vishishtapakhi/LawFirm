package com.example.demo.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Client;
import com.example.demo.model.ClientEmail;
import com.example.demo.model.ClientPhone;

import java.util.List;

@Repository
public class ClientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveClient(Client client) {
        String query = "INSERT INTO Client (FName, MName, LName, Occupation, DateOfBirth, Spouse, Address, Children) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, client.getFName(), client.getMName(), client.getLName(), client.getOccupation(), client.getDateOfBirth(), client.getSpouse(), client.getAddress(), client.getChildren());
    }

    public Integer getLastInsertId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public void saveClientPhone(Integer clientId, String phoneNumber) {
        String query = "INSERT INTO ClientPhone (clientId, phoneNumber) VALUES (?, ?)";
        jdbcTemplate.update(query, clientId, phoneNumber);
    }

    public void saveClientEmail(Integer clientId, String email) {
        String query = "INSERT INTO ClientEmail (clientId, emailAddress) VALUES (?, ?)";
        jdbcTemplate.update(query, clientId, email);
    }

    public List<Client> listClients() {
        return jdbcTemplate.query("SELECT * FROM Client", new BeanPropertyRowMapper<>(Client.class));
    }

    public Client getClientById(Integer id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM Client WHERE clientId = ?",
            new BeanPropertyRowMapper<>(Client.class),
            id
        );
    }

    public List<String> getClientPhones(Integer clientId) {
        return jdbcTemplate.query(
            "SELECT phoneNumber FROM ClientPhone WHERE clientId = ?",
            (rs, rowNum) -> rs.getString("phoneNumber"), // Use a lambda expression for mapping
            clientId
        );
    }
    

    public List<String> getClientEmails(Integer clientId) {
        return jdbcTemplate.query(
            "SELECT emailAddress FROM ClientEmail WHERE clientId = ?",
            (rs, rowNum) -> rs.getString("emailAddress"), // Use a lambda expression for mapping
            clientId
        );
    }

    public void updateClient(Client client) {
        String query = "UPDATE Client SET FName = ?, MName = ?, LName = ?, Occupation = ?, DateOfBirth = ?, Spouse = ?, Address = ?, Children = ? WHERE clientId = ?";
        jdbcTemplate.update(query, client.getFName(), client.getMName(), client.getLName(), client.getOccupation(), client.getDateOfBirth(), client.getSpouse(), client.getAddress(), client.getChildren(), client.getClientId());
    }

    public void deleteClient(Integer id) {
        jdbcTemplate.update("DELETE FROM Client WHERE clientId = ?", id);
    }

    public void deleteClientPhone(Integer clientId) {
        jdbcTemplate.update("DELETE FROM ClientPhone WHERE clientId = ?", clientId);
    }

    public void deleteClientEmail(Integer clientId) {
        jdbcTemplate.update("DELETE FROM ClientEmail WHERE clientId = ?", clientId);
    }

    // Add methods for updating phone numbers and emails
    public void updateClientPhones(Object object, List<String> newPhoneNumbers) {
        // First, delete existing phone numbers
        String deleteQuery = "DELETE FROM ClientPhone WHERE ClientID = ?";
        jdbcTemplate.update(deleteQuery, object);
    
        // Insert new phone numbers
        String insertQuery = "INSERT INTO ClientPhone (ClientID, PhoneNumber) VALUES (?, ?)";
        for (String phoneNumber : newPhoneNumbers) {
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                jdbcTemplate.update(insertQuery, object, phoneNumber);
            }
        }
    }
    
    public void updateClientEmails(Object object, List<String> newEmails) {
        // First, delete existing email addresses
        String deleteQuery = "DELETE FROM ClientEmail WHERE ClientID = ?";
        jdbcTemplate.update(deleteQuery, object);
    
        // Insert new email addresses
        String insertQuery = "INSERT INTO ClientEmail (ClientID, EmailAddress) VALUES (?, ?)";
        for (String email : newEmails) {
            if (email != null && !email.isEmpty()) {
                jdbcTemplate.update(insertQuery, object, email);
            }
        }
    }

    public List<Client> searchClients(String keyword) {
        String query = "SELECT * FROM Client WHERE FName LIKE ? OR LName LIKE ? OR Occupation LIKE ?";
        String searchKeyword = "%" + keyword + "%"; // Add wildcards for partial matching
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Client.class), searchKeyword, searchKeyword, searchKeyword);
    }

    public int countClients() {
        String query = "SELECT COUNT(*) FROM Client";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }
    
    public boolean emailExistsInClientTable(String email) {
        String query = "SELECT COUNT(*) FROM ClientEmail WHERE EmailAddress = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
        return count != null && count > 0;
    }
    
}
