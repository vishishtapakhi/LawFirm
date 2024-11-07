package com.example.demo.dao;

import com.example.demo.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class InvoiceDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new invoice
    public void saveInvoice(Invoice invoice) {
        String query = "INSERT INTO Invoice (InvoiceID, CaseID, CatID, InvoiceDate, Amount, DueDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
            invoice.getInvoiceID(),
            invoice.getCaseID(),
            invoice.getCatID(),
            Date.valueOf(invoice.getInvoiceDate().toLocalDate()), // Assuming InvoiceDate is LocalDate
            invoice.getAmount(),
            Date.valueOf(invoice.getDueDate().toLocalDate()), // Assuming DueDate is LocalDate
            invoice.getStatus()
        );
    }

    // Get a list of all invoices
    public List<Invoice> listInvoices() {
        String query = "SELECT * FROM Invoice";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("InvoiceID"));
            invoice.setCaseID(rs.getInt("CaseID"));
            invoice.setCatID(rs.getInt("CatID"));
            invoice.setInvoiceDate(rs.getDate("InvoiceDate"));
            invoice.setAmount(rs.getBigDecimal("Amount"));
            invoice.setDueDate(rs.getDate("DueDate"));
            invoice.setStatus(rs.getString("Status"));
            return invoice;
        });
    }

    // Get an invoice by its InvoiceID
    public Invoice getInvoiceById(int invoiceId) {
        String query = "SELECT * FROM Invoice WHERE InvoiceID = ?";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("InvoiceID"));
            invoice.setCaseID(rs.getInt("CaseID"));
            invoice.setCatID(rs.getInt("CatID"));
            invoice.setInvoiceDate(rs.getDate("InvoiceDate"));
            invoice.setAmount(rs.getBigDecimal("Amount"));
            invoice.setDueDate(rs.getDate("DueDate"));
            invoice.setStatus(rs.getString("Status"));
            return invoice;
        }, invoiceId);
    }

    // Update an existing invoice
    public void updateInvoice(Invoice invoice) {
        String query = "UPDATE Invoice SET CaseID = ?, CatID = ?, InvoiceDate = ?, Amount = ?, DueDate = ?, Status = ? WHERE InvoiceID = ?";
        jdbcTemplate.update(query,
            invoice.getCaseID(),
            invoice.getCatID(),
            Date.valueOf(invoice.getInvoiceDate().toLocalDate()), // Assuming InvoiceDate is LocalDate
            invoice.getAmount(),
            Date.valueOf(invoice.getDueDate().toLocalDate()), // Assuming DueDate is LocalDate
            invoice.getStatus(),
            invoice.getInvoiceID()
        );
    }

    // Delete an invoice
    public void deleteInvoice(int invoiceId) {
        String query = "DELETE FROM Invoice WHERE InvoiceID = ?";
        jdbcTemplate.update(query, invoiceId);
    }

    // Search for invoices by status or description (if applicable)
    public List<Invoice> searchInvoicesByStatus(String status) {
        String query = "SELECT * FROM Invoice WHERE Status LIKE ?";
        String searchStatus = "%" + status + "%"; // Add wildcards for partial matching
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("InvoiceID"));
            invoice.setCaseID(rs.getInt("CaseID"));
            invoice.setCatID(rs.getInt("CatID"));
            invoice.setInvoiceDate(rs.getDate("InvoiceDate"));
            invoice.setAmount(rs.getBigDecimal("Amount"));
            invoice.setDueDate(rs.getDate("DueDate"));
            invoice.setStatus(rs.getString("Status"));
            return invoice;
        }, searchStatus);
    }

    // Assign lawyers to an invoice
    public void assignLawyersToInvoice(int invoiceId, List<Integer> lawyerIds) {
        // Remove existing lawyer assignments
        String deleteLawyersQuery = "DELETE FROM InvoiceAssigned WHERE InvoiceID = ? AND EmployeeType = 'Lawyer'";
        jdbcTemplate.update(deleteLawyersQuery, invoiceId);

        // Insert new lawyer assignments
        String insertLawyerQuery = "INSERT INTO InvoiceAssigned (InvoiceID, EmployeeType, EmpID) VALUES (?, 'Lawyer', ?)";
        for (Integer lawyerId : lawyerIds) {
            jdbcTemplate.update(insertLawyerQuery, invoiceId, lawyerId);
        }
    }

    // Assign paralegals to an invoice
    public void assignParalegalsToInvoice(int invoiceId, List<Integer> paralegalIds) {
        // Remove existing paralegal assignments
        String deleteParalegalsQuery = "DELETE FROM InvoiceAssigned WHERE InvoiceID = ? AND EmployeeType = 'Paralegal'";
        jdbcTemplate.update(deleteParalegalsQuery, invoiceId);

        // Insert new paralegal assignments
        String insertParalegalQuery = "INSERT INTO InvoiceAssigned (InvoiceID, EmployeeType, EmpID) VALUES (?, 'Paralegal', ?)";
        for (Integer paralegalId : paralegalIds) {
            jdbcTemplate.update(insertParalegalQuery, invoiceId, paralegalId);
        }
    }
}
