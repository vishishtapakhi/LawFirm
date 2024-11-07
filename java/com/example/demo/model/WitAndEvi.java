package com.example.demo.model;

public class WitAndEvi {
    
    private Integer eviID;
    private String proofType;
    private String phoneNo;
    private String witName;
    private String evidenceFilePath;
    private String witnessText;

    // Getters and Setters

    public Integer getEviID() {
        return eviID;
    }

    public void setEviID(Integer eviID) {
        this.eviID = eviID;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWitName() {
        return witName;
    }

    public void setWitName(String witName) {
        this.witName = witName;
    }

    public String getEvidenceFilePath() {
        return evidenceFilePath;
    }

    public void setEvidenceFilePath(String evidenceFilePath) {
        this.evidenceFilePath = evidenceFilePath;
    }

    public String getWitnessText() {
        return witnessText;
    }

    public void setWitnessText(String witnessText) {
        this.witnessText = witnessText;
    }
}
