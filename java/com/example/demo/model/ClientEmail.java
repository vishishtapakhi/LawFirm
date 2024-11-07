package com.example.demo.model;

public class ClientEmail {
    private Integer clientId;
    private String emailAddress;

    // No-argument constructor
    public ClientEmail() {
    }

    // Constructor with parameters
    public ClientEmail(Integer clientId, String emailAddress) {
        this.clientId = clientId;
        this.emailAddress = emailAddress;
    }

    // Getters
    public Integer getClientId() {
        return clientId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // If you want to return the ID (if there's an ID field later)
    public Object getId() {
        return null; // Modify this as per your actual ID logic, if any
    }
}

