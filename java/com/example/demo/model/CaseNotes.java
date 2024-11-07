package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Date;

public class CaseNotes {
    private int caseNoteID;  // Corresponds to CaseNoteID
    private String noteText;  // Corresponds to NoteText
    private String relevance;  // Corresponds to Relevance
    private Date dateCreated;  // Corresponds to DateCreated
    private Date dateModified;  // Corresponds to DateModified
    private int caseID;  // Corresponds to CaseID
    private int catID;  // Corresponds to CatID
    private int lawyerID;  // Corresponds to LawyerID

    // Default constructor
    public CaseNotes() {}

    // Parameterized constructor
    public CaseNotes(int caseNoteID, String noteText, String relevance, Date dateCreated, Date dateModified, int caseID, int catID, int lawyerID) {
        this.caseNoteID = caseNoteID;
        this.noteText = noteText;
        this.relevance = relevance;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.caseID = caseID;
        this.catID = catID;
        this.lawyerID = lawyerID;
    }

    // Getters and Setters
    public int getCaseNoteID() {
        return caseNoteID;
    }

    public void setCaseNoteID(int caseNoteID) {
        this.caseNoteID = caseNoteID;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(java.sql.Date date) {
        this.dateCreated = date;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(java.sql.Date date) {
        this.dateModified = date;
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

    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
    }
}
