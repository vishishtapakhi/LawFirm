package com.example.demo.model;
import java.util.Date;


public class MatrimonialCase {
    private int matrimonialCaseID;
    private String caseDesc;
    private String startDate;
    private String endDate;
    private int clientID;
    private String marriedStatus;
    private String disputeType;
    private String alimonyStatus;
    private int children;
    private String custodyDetail;
    private String marriageDate;

    // Getters and Setters
    public int getMatrimonialCaseID() {
        return matrimonialCaseID;
    }

    public void setMatrimonialCaseID(int matrimonialCaseID) {
        this.matrimonialCaseID = matrimonialCaseID;
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

    public String getMarriedStatus() {
        return marriedStatus;
    }

    public void setMarriedStatus(String marriedStatus) {
        this.marriedStatus = marriedStatus;
    }

    public String getDisputeType() {
        return disputeType;
    }

    public void setDisputeType(String disputeType) {
        this.disputeType = disputeType;
    }

    public String getAlimonyStatus() {
        return alimonyStatus;
    }

    public void setAlimonyStatus(String alimonyStatus) {
        this.alimonyStatus = alimonyStatus;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getCustodyDetail() {
        return custodyDetail;
    }

    public void setCustodyDetail(String custodyDetail) {
        this.custodyDetail = custodyDetail;
    }

    public String getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate;
    }
}
