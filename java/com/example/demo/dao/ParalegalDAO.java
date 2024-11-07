package com.example.demo.dao;

import com.example.demo.model.Lawyer;
import com.example.demo.model.Paralegal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParalegalDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveParalegal(Paralegal paralegal) {
        String query = "INSERT INTO Paralegal (FName, MName, LName, DateOfBirth, Qualification, Experience, Positions) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, paralegal.getFName(), paralegal.getMName(), paralegal.getLName(), paralegal.getDateOfBirth(), paralegal.getQualification(), paralegal.getExperience(), paralegal.getPositions());
    }

    public Integer getLastInsertId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public void saveParalegalPhone(Integer paralegalId, String phoneNumber) {
        String query = "INSERT INTO ParalegalPhone (ParalegalID, PhoneNumber) VALUES (?, ?)";
        jdbcTemplate.update(query, paralegalId, phoneNumber);
    }

    public void saveParalegalEmail(Integer paralegalId, String email) {
        String query = "INSERT INTO ParalegalEmail (ParalegalID, EmailAddress) VALUES (?, ?)";
        jdbcTemplate.update(query, paralegalId, email);
    }

    public List<Paralegal> listParalegals() {
        return jdbcTemplate.query("SELECT * FROM Paralegal", new BeanPropertyRowMapper<>(Paralegal.class));
    }

    public Paralegal getParalegalById(Integer id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM Paralegal WHERE ParalegalID = ?",
            new BeanPropertyRowMapper<>(Paralegal.class),
            id
        );
    }

    public List<Paralegal> getParalegalByEmail(String email) {
        // First, query the LawyerEmail table to get the LawyerID for the given email address
        String emailQuery = "SELECT ParalegalID FROM ParalegalEmail WHERE EmailAddress = ?";
        
        List<Integer> paralegalIds = jdbcTemplate.query(emailQuery, (rs, rowNum) -> rs.getInt("ParalegalID"), email);
    
        if (paralegalIds.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if no LawyerID is found
        }
    
        // Then, query the Lawyer table using the retrieved LawyerID(s)
        String paralegalQuery = "SELECT * FROM Paralegal WHERE ParalegalID = ?";
        
        List<Paralegal> paralegals = new ArrayList<>();
        for (Integer paralegalId : paralegalIds) {
            paralegals.add(jdbcTemplate.queryForObject(paralegalQuery, new BeanPropertyRowMapper<>(Paralegal.class), paralegalId));
        }
    
        return paralegals;
    }
    

    public List<String> getParalegalPhones(Integer paralegalId) {
        return jdbcTemplate.query(
            "SELECT PhoneNumber FROM ParalegalPhone WHERE ParalegalID = ?",
            (rs, rowNum) -> rs.getString("PhoneNumber"),
            paralegalId
        );
    }

    public List<String> getParalegalEmails(Integer paralegalId) {
        return jdbcTemplate.query(
            "SELECT EmailAddress FROM ParalegalEmail WHERE ParalegalID = ?",
            (rs, rowNum) -> rs.getString("Email"),
            paralegalId
        );
    }

    public void updateParalegal(Paralegal paralegal) {
        String query = "UPDATE Paralegal SET FName = ?, MName = ?, LName = ?, DateOfBirth = ?, Qualification = ?, Experience = ?, Positions = ? WHERE ParalegalID = ?";
        jdbcTemplate.update(query, paralegal.getFName(), paralegal.getMName(), paralegal.getLName(), paralegal.getDateOfBirth(), paralegal.getQualification(), paralegal.getExperience(), paralegal.getPositions(), paralegal.getParalegalID());
    }

    public void deleteParalegal(Integer id) {
        jdbcTemplate.update("DELETE FROM Paralegal WHERE ParalegalID = ?", id);
    }

    public void deleteParalegalPhone(Integer paralegalId) {
        jdbcTemplate.update("DELETE FROM ParalegalPhone WHERE ParalegalID = ?", paralegalId);
    }

    public void deleteParalegalEmail(Integer paralegalId) {
        jdbcTemplate.update("DELETE FROM ParalegalEmail WHERE ParalegalID = ?", paralegalId);
    }

    public void updateParalegalPhones(Integer paralegalId, List<String> newPhoneNumbers) {
        String deleteQuery = "DELETE FROM ParalegalPhone WHERE ParalegalID = ?";
        jdbcTemplate.update(deleteQuery, paralegalId);

        String insertQuery = "INSERT INTO ParalegalPhone (ParalegalID, PhoneNumber) VALUES (?, ?)";
        for (String phoneNumber : newPhoneNumbers) {
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                jdbcTemplate.update(insertQuery, paralegalId, phoneNumber);
            }
        }
    }

    public void updateParalegalEmails(Integer paralegalId, List<String> newEmails) {
        String deleteQuery = "DELETE FROM ParalegalEmail WHERE ParalegalID = ?";
        jdbcTemplate.update(deleteQuery, paralegalId);

        String insertQuery = "INSERT INTO ParalegalEmail (ParalegalID, EmailAddress) VALUES (?, ?)";
        for (String email : newEmails) {
            if (email != null && !email.isEmpty()) {
                jdbcTemplate.update(insertQuery, paralegalId, email);
            }
        }
    }

    public List<Paralegal> searchParalegals(String keyword) {
        String query = "SELECT * FROM Paralegal WHERE FName LIKE ? OR LName LIKE ? OR Specialization LIKE ?";
        String searchKeyword = "%" + keyword + "%"; // Add wildcards for partial matching
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Paralegal.class), searchKeyword, searchKeyword, searchKeyword);
    }

    public int countParalegals() {
        String query = "SELECT COUNT(*) FROM Paralegal";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }

    public boolean emailExistsInParalegalTable(String email) {
        String query = "SELECT COUNT(*) FROM ParalegalEmail WHERE EmailAddress = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
        return count != null && count > 0;
    }
    
    
}
