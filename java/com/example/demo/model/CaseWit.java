package com.example.demo.model;


public class CaseWit  {
    
    private int eviID;
    private int caseID;
    private int catID;

    // Default constructor
    public CaseWit() {
    }

    // Parameterized constructor
    public CaseWit(int eviID, int caseID, int catID) {
        this.eviID = eviID;
        this.caseID = caseID;
        this.catID = catID;
    }

    // Getters and Setters
    public int getEviID() {
        return eviID;
    }

    public void setEviID(int eviID) {
        this.eviID = eviID;
    }

    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }


 
}
