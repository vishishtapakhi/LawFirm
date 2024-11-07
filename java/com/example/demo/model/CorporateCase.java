package com.example.demo.model;

public class CorporateCase {
    private int corporateCaseID;
    private String caseDesc;
    private String startDate;
    private String endDate;
    private int clientID;
    private String caseStatus;

    // Getters and setters
    public int getCorporateCaseID() {
        return corporateCaseID;
    }

    public void setCorporateCaseID(int corporateCaseID) {
        this.corporateCaseID = corporateCaseID;
    }


    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
}
