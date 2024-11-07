package com.example.demo.dao;

import com.example.demo.model.WitAndEvi;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WitAndEviDAO {

    private final JdbcTemplate jdbcTemplate;

    public WitAndEviDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Retrieve all WitAndEvi records
    public List<WitAndEvi> getAllWitAndEvi() {
        String sql = "SELECT * FROM WitAndEvi";
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        List<WitAndEvi> witAndEviList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            WitAndEvi witAndEvi = new WitAndEvi();
            witAndEvi.setEviID((Integer) row.get("EviID"));
            witAndEvi.setProofType((String) row.get("ProofType"));
            witAndEvi.setPhoneNo((String) row.get("PhoneNo"));
            witAndEvi.setWitName((String) row.get("WitName"));
            witAndEvi.setEvidenceFilePath((String) row.get("EvidenceFilePath"));
            witAndEvi.setWitnessText((String) row.get("WitnessText"));
            witAndEviList.add(witAndEvi);
        }
        
        return witAndEviList;
    }

    // Retrieve a specific WitAndEvi record by its ID
    public WitAndEvi getWitAndEviById(int eviID) {
        String sql = "SELECT * FROM WitAndEvi WHERE EviID = ?";
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, eviID);
        
        if (rows.isEmpty()) {
            return null;
        } else {
            Map<String, Object> row = rows.get(0);
            WitAndEvi witAndEvi = new WitAndEvi();
            witAndEvi.setEviID((Integer) row.get("EviID"));
            witAndEvi.setProofType((String) row.get("ProofType"));
            witAndEvi.setPhoneNo((String) row.get("PhoneNo"));
            witAndEvi.setWitName((String) row.get("WitName"));
            witAndEvi.setEvidenceFilePath((String) row.get("EvidenceFilePath"));
            witAndEvi.setWitnessText((String) row.get("WitnessText"));
            return witAndEvi;
        }
    }
    
    


    // Save a new WitAndEvi record
    public void saveWitAndEvi(WitAndEvi witAndEvi) {
        String sql = "INSERT INTO WitAndEvi (ProofType, PhoneNo, WitName, EvidenceFilePath, WitnessText) " +
                     "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
                            witAndEvi.getProofType(), 
                            witAndEvi.getPhoneNo(), 
                            witAndEvi.getWitName(), 
                            witAndEvi.getEvidenceFilePath(), 
                            witAndEvi.getWitnessText());
    }

    // Update an existing WitAndEvi record
    public void updateWitAndEvi(WitAndEvi witAndEvi) {
        String sql = "UPDATE WitAndEvi SET ProofType = ?, PhoneNo = ?, WitName = ?, EvidenceFilePath = ?, WitnessText = ? " +
                     "WHERE EviID = ?";
        jdbcTemplate.update(sql, 
                            witAndEvi.getProofType(), 
                            witAndEvi.getPhoneNo(), 
                            witAndEvi.getWitName(), 
                            witAndEvi.getEvidenceFilePath(), 
                            witAndEvi.getWitnessText(),
                            witAndEvi.getEviID());
    }

    // Delete a WitAndEvi record by its ID
    public void deleteWitAndEvi(int eviID) {
        String sql = "DELETE FROM WitAndEvi WHERE EviID = ?";
        jdbcTemplate.update(sql, eviID);
    }
}
