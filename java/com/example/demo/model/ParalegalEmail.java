package com.example.demo.model;

public class ParalegalEmail {
    private Integer paralegalId;       // ParalegalID as a foreign key
    private String emailAddress;

    // No-argument constructor
    public ParalegalEmail() {
    }

    // Constructor with parameters
    public ParalegalEmail(Integer paralegalId, String emailAddress) {
        this.paralegalId = paralegalId;
        this.emailAddress = emailAddress;
    }

    // Getters
    public Integer getParalegalId() {
        return paralegalId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // If you want to return the ID (if there's an ID field later)
    public Object getId() {
        return null; // Modify this as per your actual ID logic, if any
    }
}
