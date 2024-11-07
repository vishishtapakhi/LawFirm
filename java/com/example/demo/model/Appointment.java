package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Appointment {

    private int appointmentID;   // Corresponds to AppointmentID
    private int catID;           // Corresponds to CatID
    private int caseID;          // Corresponds to CaseID
    private int lawyerID;        // Corresponds to LawyerID      
    private LocalDate appointmentDate;  // Corresponds to AppointmentDate
    private LocalTime appointmentTime;  // Corresponds to AppointmentTime
    private String location;     // Corresponds to Location

    private Map<String, Object> additionalAttributes = new HashMap<>();

    public void setAdditionalAttribute(String key, Object value) {
        additionalAttributes.put(key, value);
    }

    // Method to get dynamic attributes (like caseType)
    public Object getAdditionalAttribute(String key) {
        return additionalAttributes.get(key);
    }

    // Getters and Setters
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
    }
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
