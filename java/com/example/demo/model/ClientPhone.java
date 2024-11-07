package com.example.demo.model;

public class ClientPhone {
    private Integer clientId;
    private String phoneNumber;

    // No-argument constructor
    public ClientPhone() {
    }

    // Constructor with parameters
    public ClientPhone(Integer clientId, String phoneNumber) {
        this.clientId = clientId;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public Integer getClientId() {
        return clientId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
