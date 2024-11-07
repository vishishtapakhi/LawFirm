package com.example.demo.dao;

import com.example.demo.model.CorporateCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CorporateCaseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new corporate case
    public void saveCorporateCase(CorporateCase corporateCase) {
        String sql = "INSERT INTO CorporateCase (CaseDesc, StartDate, EndDate, ClientID, CaseStatus) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, corporateCase.getCaseDesc(),
                corporateCase.getStartDate(), corporateCase.getEndDate(), corporateCase.getClientID(), corporateCase.getCaseStatus());
    }

    // List all corporate cases
    public List<CorporateCase> listCorporateCases() {
        String sql = "SELECT * FROM CorporateCase";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CorporateCase.class));
    }

    // Get a corporate case by ID
    public CorporateCase getCorporateCaseById(int corporateCaseID) {
        String sql = "SELECT * FROM CorporateCase WHERE CorporateCaseID = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(CorporateCase.class),
                corporateCaseID
            );
    }

    // Update an existing corporate case
    public void updateCorporateCase(CorporateCase corporateCase) {
        String sql = "UPDATE CorporateCase SET  CaseDesc = ?, StartDate = ?, EndDate = ?, ClientID = ?, CaseStatus = ? WHERE CorporateCaseID = ?";
        jdbcTemplate.update(sql, corporateCase.getCaseDesc(), corporateCase.getStartDate(),
                corporateCase.getEndDate(), corporateCase.getClientID(), corporateCase.getCaseStatus(), corporateCase.getCorporateCaseID());
    }

    public List<Integer> getCorporateCaseIds() {
        String sql = "SELECT corporateCaseID FROM CorporateCase";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    // Delete a corporate case
    public void deleteCorporateCase(int corporateCaseID) {
        String sql = "DELETE FROM CorporateCase WHERE CorporateCaseID = ?";
        jdbcTemplate.update(sql, corporateCaseID);
    }

    public int countCc() {
        String query = "SELECT COUNT(*) FROM CorporateCase";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }
   
}
