package com.example.demo.model;

public class Category {
    private int catID;          // Primary key
    private String caseType;    // Case type (string)

    // Constructor
    public Category() {}

    public Category(int catID, String caseType) {
        this.catID = catID;
        this.caseType = caseType;
    }

    // Getters and setters
    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
