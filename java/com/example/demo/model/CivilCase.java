package com.example.demo.model;



public class CivilCase {
    private int civilCaseID;
    private String caseDesc;
    private String startDate;
    private String endDate;
    private String appeal;
    private int clientID;
    private int compensation;
    private String location;
    private String disputeType;
    private String mediationState;

    // Getters and Setters
    public int getCivilCaseID() {
        return civilCaseID;
    }

    public void setCivilCaseID(int civilCaseID) {
        this.civilCaseID = civilCaseID;
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

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCompensation() {
        return compensation;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDisputeType() {
        return disputeType;
    }

    public void setDisputeType(String disputeType) {
        this.disputeType = disputeType;
    }

    public String getMediationState() {
        return mediationState;
    }

    public void setMediationState(String mediationState) {
        this.mediationState = mediationState;
    }
}
