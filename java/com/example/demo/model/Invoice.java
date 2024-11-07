package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Invoice {

    private int invoiceID;
    private int caseID;
    private int catID;
    private Date invoiceDate;
    private double amount;
    private Date dueDate;
    private String status;

    // Default constructor
    public Invoice() {
    }

    // Parameterized constructor
    public Invoice(int invoiceID, int caseID, int catID, Date invoiceDate, double amount, Date dueDate, String status) {
        this.invoiceID = invoiceID;
        this.caseID = caseID;
        this.catID = catID;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and setters
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceID +
                ", caseID=" + caseID +
                ", catID=" + catID +
                ", invoiceDate=" + invoiceDate +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                '}';
    }

    public void setAmount(BigDecimal bigDecimal) {
    }
}
