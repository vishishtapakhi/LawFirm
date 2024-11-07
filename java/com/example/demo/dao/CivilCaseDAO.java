package com.example.demo.dao;

import com.example.demo.model.CivilCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CivilCaseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new civil case
    public void saveCivilCase(CivilCase civilCase) {
        String sql = "INSERT INTO CivilCase (CaseDesc, StartDate, EndDate, Appeal, ClientID, Compensation, Location, DisputeType, MediationState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, civilCase.getCaseDesc(), civilCase.getStartDate(),
                civilCase.getEndDate(), civilCase.getAppeal(), civilCase.getClientID(),
                civilCase.getCompensation(), civilCase.getLocation(), civilCase.getDisputeType(),
                civilCase.getMediationState());
    }

    // List all civil cases
    public List<CivilCase> listCivilCases() {
        String sql = "SELECT * FROM CivilCase";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CivilCase.class));
    }

    // Get a civil case by ID
    public CivilCase getCivilCaseById(int civilCaseID) {
        String sql = "SELECT * FROM CivilCase WHERE CivilCaseID = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(CivilCase.class),
                civilCaseID
        );
    }

    // Update an existing civil case
    public void updateCivilCase(CivilCase civilCase) {
        String sql = "UPDATE CivilCase SET CaseDesc = ?, StartDate = ?, EndDate = ?, Appeal = ?, ClientID = ?, Compensation = ?, Location = ?, DisputeType = ?, MediationState = ? WHERE CivilCaseID = ?";
        jdbcTemplate.update(sql, civilCase.getCaseDesc(), civilCase.getStartDate(),
                civilCase.getEndDate(), civilCase.getAppeal(), civilCase.getClientID(),
                civilCase.getCompensation(), civilCase.getLocation(), civilCase.getDisputeType(),
                civilCase.getMediationState(), civilCase.getCivilCaseID());
    }

    // Retrieve all CivilCase IDs
    public List<Integer> getCivilCaseIds() {
        String sql = "SELECT CivilCaseID FROM CivilCase";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    // Delete a civil case
    public void deleteCivilCase(int civilCaseID) {
        String sql = "DELETE FROM CivilCase WHERE CivilCaseID = ?";
        jdbcTemplate.update(sql, civilCaseID);
    }

    public int countciv() {
        String query = "SELECT COUNT(*) FROM CivilCase";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }
}
