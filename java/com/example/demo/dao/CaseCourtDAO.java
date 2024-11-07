// package com.example.demo.dao;

// import com.example.demo.model.CaseCourt;
// import com.example.demo.model.Category;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public class CaseCourtDAO {

//     @Autowired
//     private JdbcTemplate jdbcTemplate;

//     // Assign a case to a court
//     public void assignCaseToCourt(CaseCourt caseCourt) {
//         String query = "INSERT INTO CaseCourt (CourtID, CaseID, CatID) VALUES (?, ?, ?)";
//         jdbcTemplate.update(query, caseCourt.getCourtID(), caseCourt.getCaseID(), caseCourt.getCatID());
//     }

//     // Get all cases (for dropdown in form)
//     public List<Case> getAllCases() {
//         String query = "SELECT * FROM Case"; // Adjust this query as per your actual table structure
//         return jdbcTemplate.query(query, (rs, rowNum) -> {
//             Case caseObj = new Case();
//             caseObj.setCaseID(rs.getInt("CaseID"));
//             caseObj.setCaseDesc(rs.getString("CaseDesc"));
//             return caseObj;
//         });
//     }

//     // Get all categories (for dropdown in form)
//     public List<Category
package com.example.demo.dao;

import com.example.demo.model.CaseCourt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Repository
public class CaseCourtDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // List all CaseCourt associations
    public List<CaseCourt> listCaseCourts() {
        String sql = "SELECT * FROM CaseCourt";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CaseCourt.class));
    }

    // Get CaseCourt by Case ID and Court ID
    public CaseCourt getCaseCourtByIds(Integer caseId, Integer courtId) {
        String sql = "SELECT * FROM CaseCourt WHERE CaseID = ? AND CourtID = ?";
        try {
            return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(CaseCourt.class),
                caseId,
                courtId
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No CaseCourt found with CaseID: " + caseId + " and CourtID: " + courtId);
            return null; // or throw a custom exception
        } catch (DataAccessException e) {
            System.out.println("Error retrieving CaseCourt: " + e.getMessage());
            return null; // or throw a custom exception
        }
    }

    // Save a new CaseCourt relationship
    public void save(CaseCourt caseCourt) {
        String sql = "INSERT INTO CaseCourt (CourtID, CaseID, CatID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, caseCourt.getCourtID(), caseCourt.getCaseID(), caseCourt.getCatID()); // Adjust as per your CaseCourt entity
    }

    // Update an existing CaseCourt relationship
    public void update(CaseCourt caseCourt) {
        String sql = "UPDATE CaseCourt SET CatID = ? WHERE CaseID = ? AND CourtID = ?";
        jdbcTemplate.update(sql, caseCourt.getCatID(), caseCourt.getCaseID(), caseCourt.getCourtID());
    }

    // Delete a CaseCourt relationship
    public void delete(Integer caseId, Integer courtId) {
        String sql = "DELETE FROM CaseCourt WHERE CaseID = ? AND CourtID = ?";
        jdbcTemplate.update(sql, caseId, courtId);
    }

    // List all courts for a specific case
    public List<CaseCourt> getCourtsByCaseId(Integer caseId) {
        String sql = "SELECT * FROM CaseCourt WHERE CaseID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CaseCourt.class), caseId);
    }
}
