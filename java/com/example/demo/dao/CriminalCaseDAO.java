package com.example.demo.dao;

import com.example.demo.model.CriminalCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CriminalCaseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new criminal case
    public void saveCriminalCase(CriminalCase criminalCase) {
        String sql = "INSERT INTO CriminalCase (CaseDesc, StartDate, ArrestDate, Appeal, EndDate, ClientID) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, criminalCase.getCaseDesc(), criminalCase.getStartDate(),
                criminalCase.getArrestDate(), criminalCase.getAppeal(), criminalCase.getEndDate(),
                criminalCase.getClientID());
    }

    // List all criminal cases
    public List<CriminalCase> listCriminalCases() {
        String sql = "SELECT * FROM CriminalCase";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CriminalCase.class));
    }

    // Get a criminal case by ID
    public CriminalCase getCriminalCaseById(int criminalCaseID) {
        String sql = "SELECT * FROM CriminalCase WHERE CriminalCaseID = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(CriminalCase.class),
                criminalCaseID
        );
    }

    // Update an existing criminal case
    public void updateCriminalCase(CriminalCase criminalCase) {
        String sql = "UPDATE CriminalCase SET CaseDesc = ?, StartDate = ?, ArrestDate = ?, Appeal = ?, EndDate = ?, ClientID = ? WHERE CriminalCaseID = ?";
        jdbcTemplate.update(sql, criminalCase.getCaseDesc(), criminalCase.getStartDate(),
                criminalCase.getArrestDate(), criminalCase.getAppeal(), criminalCase.getEndDate(),
                criminalCase.getClientID(), criminalCase.getCriminalCaseID());
    }

    // Retrieve all CriminalCase IDs
    public List<Integer> getCriminalCaseIds() {
        String sql = "SELECT CriminalCaseID FROM CriminalCase";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    // Delete a criminal case
    public void deleteCriminalCase(int criminalCaseID) {
        String sql = "DELETE FROM CriminalCase WHERE CriminalCaseID = ?";
        jdbcTemplate.update(sql, criminalCaseID);
    }

    public int countcc() {
        String query = "SELECT COUNT(*) FROM CriminalCase";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }
}
