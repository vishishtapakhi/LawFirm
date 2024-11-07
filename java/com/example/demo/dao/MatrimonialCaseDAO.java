package com.example.demo.dao;

import com.example.demo.model.MatrimonialCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatrimonialCaseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new matrimonial case
    public void saveMatrimonialCase(MatrimonialCase matrimonialCase) {
        String sql = "INSERT INTO MatrimonialCase (CaseDesc, StartDate, EndDate, ClientID, MarriedStatus, DisputeType, AlimonyStatus, Children, CustodyDetail, MarriageDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, matrimonialCase.getCaseDesc(), matrimonialCase.getStartDate(),
                matrimonialCase.getEndDate(), matrimonialCase.getClientID(), matrimonialCase.getMarriedStatus(),
                matrimonialCase.getDisputeType(), matrimonialCase.getAlimonyStatus(),
                matrimonialCase.getChildren(), matrimonialCase.getCustodyDetail(),
                matrimonialCase.getMarriageDate());
    }

    // List all matrimonial cases
    public List<MatrimonialCase> listMatrimonialCases() {
        String sql = "SELECT * FROM MatrimonialCase";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MatrimonialCase.class));
    }

    // Get a matrimonial case by ID
    public MatrimonialCase getMatrimonialCaseById(int matrimonialCaseID) {
        String sql = "SELECT * FROM MatrimonialCase WHERE MatrimonialCaseID = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(MatrimonialCase.class),
                matrimonialCaseID
        );
    }

    // Update an existing matrimonial case
    public void updateMatrimonialCase(MatrimonialCase matrimonialCase) {
        String sql = "UPDATE MatrimonialCase SET CaseDesc = ?, StartDate = ?, EndDate = ?, ClientID = ?, MarriedStatus = ?, DisputeType = ?, AlimonyStatus = ?, Children = ?, CustodyDetail = ?, MarriageDate = ? WHERE MatrimonialCaseID = ?";
        jdbcTemplate.update(sql, matrimonialCase.getCaseDesc(), matrimonialCase.getStartDate(),
                matrimonialCase.getEndDate(), matrimonialCase.getClientID(), matrimonialCase.getMarriedStatus(),
                matrimonialCase.getDisputeType(), matrimonialCase.getAlimonyStatus(),
                matrimonialCase.getChildren(), matrimonialCase.getCustodyDetail(),
                matrimonialCase.getMarriageDate(), matrimonialCase.getMatrimonialCaseID());
    }

    // Retrieve all MatrimonialCase IDs
    public List<Integer> getMatrimonialCaseIds() {
        String sql = "SELECT MatrimonialCaseID FROM MatrimonialCase";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    // Delete a matrimonial case
    public void deleteMatrimonialCase(int matrimonialCaseID) {
        String sql = "DELETE FROM MatrimonialCase WHERE MatrimonialCaseID = ?";
        jdbcTemplate.update(sql, matrimonialCaseID);
    }

    public int countmat() {
        String query = "SELECT COUNT(*) FROM MatrimonialCase";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }
}
