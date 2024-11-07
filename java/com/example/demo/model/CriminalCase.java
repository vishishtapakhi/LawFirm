package com.example.demo.model;
import java.util.Date;


public class CriminalCase {
    private int criminalCaseID;
    private String caseDesc;
    private String startDate;
    private String arrestDate;
    private String appeal;
    private String endDate;
    private int clientID;

    // Getters and Setters
    public int getCriminalCaseID() {
        return criminalCaseID;
    }

    public void setCriminalCaseID(int criminalCaseID) {
        this.criminalCaseID = criminalCaseID;
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

    public String getArrestDate() {
        return arrestDate;
    }

    public void setArrestDate(String arrestDate) {
        this.arrestDate = arrestDate;
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
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
}
