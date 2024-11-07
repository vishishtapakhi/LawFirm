package com.example.demo.model;

public class ParalegalPhone {
    private Integer paralegalId;       // ParalegalID as a foreign key
    private String phoneNumber;

    // No-argument constructor
    public ParalegalPhone() {
    }

    // Constructor with parameters
    public ParalegalPhone(Integer paralegalId, String phoneNumber) {
        this.paralegalId = paralegalId;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public Integer getParalegalId() {
        return paralegalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
