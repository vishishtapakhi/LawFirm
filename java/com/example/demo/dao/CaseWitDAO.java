package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CaseWit;

@Repository
public class CaseWitDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to assign case to witness using EviID, CaseID, and CatID
    public void assignCaseToWitness(int eviID, int catID, int caseID) {
        String sql = "INSERT INTO CaseWit (EviID, CaseID, CatID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, eviID, caseID, catID);
    }

    // New method to save CaseWit object
    public void save(CaseWit caseWit) {
        String sql = "INSERT INTO CaseWit (EviID, CaseID, CatID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, caseWit.getEviID(), caseWit.getCaseID(), caseWit.getCatID());
    }
}
