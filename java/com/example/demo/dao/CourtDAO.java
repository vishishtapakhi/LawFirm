package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Court;

import java.util.List;

@Repository
public class CourtDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new court
    public void saveCourt(Court court) {
        String query = "INSERT INTO Court (CourtName, CourtPincode, CourtState, CourtCity, jFName, jMName, jLName) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                court.getCourtName(),
                court.getCourtPincode(),
                court.getCourtState(),
                court.getCourtCity(),
                court.getjFName(),
                court.getjMName(),
                court.getjLName()
        );
    }

    // Get a list of all courts
    public List<Court> listCourts() {
        String query = "SELECT * FROM Court";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Court.class));
    }

    // Get court by ID
    public Court getCourtById(Integer id) {
        String query = "SELECT * FROM Court WHERE CourtID = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Court.class), id);
    }

    // Update a court
    public void updateCourt(Court court) {
        String query = "UPDATE Court SET CourtName = ?, CourtPincode = ?, CourtState = ?, CourtCity = ?, jFName = ?, jMName = ?, jLName = ? WHERE CourtID = ?";
        jdbcTemplate.update(query,
                court.getCourtName(),
                court.getCourtPincode(),
                court.getCourtState(),
                court.getCourtCity(),
                court.getjFName(),
                court.getjMName(),
                court.getjLName(),
                court.getCourtID() // Court ID for the update
        );
    }

    // Delete a court
    public void deleteCourt(Integer id) {
        String query = "DELETE FROM Court WHERE CourtID = ?";
        jdbcTemplate.update(query, id);
    }

    public int countCourts() {
        String query = "SELECT COUNT(*) FROM Court";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }
}
