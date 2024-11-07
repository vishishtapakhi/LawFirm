package com.example.demo.model;

public class LawyerPhone {
    private Integer lawyerId;          // LawyerID as a foreign key
    private String phoneNumber;

    // No-argument constructor
    public LawyerPhone() {
    }

    // Constructor with parameters
    public LawyerPhone(Integer lawyerId, String phoneNumber) {
        this.lawyerId = lawyerId;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public Integer getLawyerId() {
        return lawyerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
