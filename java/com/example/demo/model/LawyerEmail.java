package com.example.demo.model;

public class LawyerEmail {
    private Integer lawyerId;          // LawyerID as a foreign key
    private String emailAddress;

    // No-argument constructor
    public LawyerEmail() {
    }

    // Constructor with parameters
    public LawyerEmail(Integer lawyerId, String emailAddress) {
        this.lawyerId = lawyerId;
        this.emailAddress = emailAddress;
    }

    // Getters
    public Integer getLawyerId() {
        return lawyerId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // If you want to return the ID (if there's an ID field later)
    public Object getId() {
        return null; // Modify this as per your actual ID logic, if any
    }
}
