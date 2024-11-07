package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {
    private final DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CommonDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean checkEmailExistsInAllTables(String email) {
        return checkEmailInClient(email) || 
               checkEmailInLawyer(email) || 
               checkEmailInParalegal(email);
    }

    private boolean checkEmailInClient(String email) {
        String query = "SELECT COUNT(*) FROM ClientEmail WHERE emailAddress = ?";
        return executeCountQuery(query, email);
    }

    private boolean checkEmailInLawyer(String email) {
        String query = "SELECT COUNT(*) FROM LawyerEmail WHERE emailAddress = ?";
        return executeCountQuery(query, email);
    }

    private boolean checkEmailInParalegal(String email) {
        String query = "SELECT COUNT(*) FROM ParalegalEmail WHERE emailAddress = ?";
        return executeCountQuery(query, email);
    }

    public boolean checkPhoneExistsInAllTables(String phoneNumber) {
        return checkPhoneInClient(phoneNumber) || 
               checkPhoneInLawyer(phoneNumber) || 
               checkPhoneInParalegal(phoneNumber);
    }

    private boolean checkPhoneInClient(String phoneNumber) {
        String query = "SELECT COUNT(*) FROM ClientPhone WHERE phoneNumber = ?";
        return executeCountQuery(query, phoneNumber);
    }

    private boolean checkPhoneInLawyer(String phoneNumber) {
        String query = "SELECT COUNT(*) FROM LawyerPhone WHERE phoneNumber = ?";
        return executeCountQuery(query, phoneNumber);
    }

    private boolean checkPhoneInParalegal(String phoneNumber) {
        String query = "SELECT COUNT(*) FROM ParalegalPhone WHERE phoneNumber = ?";
        return executeCountQuery(query, phoneNumber);
    }

    // Helper method to execute count queries
    private boolean executeCountQuery(String query, String parameter) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, parameter);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; // Return true if count > 0
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error executing query: " + query, e);
        }
    }
    
    // Custom runtime exception for data access issues
    public static class DataAccessException extends RuntimeException {
        public DataAccessException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public boolean checkEmailExistsInClientTableExcludingId(String email, Integer clientId) {
        String sql = "SELECT COUNT(*) FROM clientemail WHERE emailAddress = ? AND clientId != ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email, clientId) > 0;
    }

    public boolean checkEmailExistsInParalegalTableExcludingId(String email, Integer paralegalId) {
        String sql = "SELECT COUNT(*) FROM paralegalemail WHERE emailAddress = ? AND paralegalId != ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email, paralegalId) > 0;
    }

    public boolean checkEmailExistsInLawyerTableExcludingId(String email, Integer lawyerId) {
        String sql = "SELECT COUNT(*) FROM lawyeremail WHERE emailAddress = ? AND lawyerId != ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email, lawyerId) > 0;
    }
    
    
    public boolean checkEmailExistsInUsersTable(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email) > 0;
    }
    
    public boolean checkEmailExistsInLawyerTable(String email) {
        String sql = "SELECT COUNT(*) FROM lawyeremail WHERE emailAddress = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email) > 0;
    }
    
    public boolean checkEmailExistsInParalegalTable(String email) {
        String sql = "SELECT COUNT(*) FROM paralegalemail WHERE emailAddress = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email) > 0;
    }
    
    public boolean checkPhoneExistsInClientTableExcludingId(String phone, Integer clientId) {
        String sql = "SELECT COUNT(*) FROM clientphone WHERE phoneNumber = ? AND clientId != ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phone, clientId) > 0;
    }
    
    public boolean checkPhoneExistsInLawyerTable(String phone) {
        String sql = "SELECT COUNT(*) FROM lawyerphone WHERE phoneNumber = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phone) > 0;
    }
    
    public boolean checkPhoneExistsInParalegalTable(String phone) {
        String sql = "SELECT COUNT(*) FROM paralegalphone WHERE phoneNumber = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phone) > 0;
    }

    public boolean checkPhoneExistsInParalegalTableExcludingId(String phone, Integer paralegalId) {
        String sql = "SELECT COUNT(*) FROM paralegalphone WHERE phoneNumber = ? AND paralegalId != ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phone, paralegalId) > 0;
    }
    
    public boolean checkEmailExistsInClientTable(String email) {
        String sql = "SELECT COUNT(*) FROM clientemail WHERE emailAddress = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email) > 0;
    }
    
    public boolean checkPhoneExistsInClientTable(String phone) {
        String sql = "SELECT COUNT(*) FROM clientphone WHERE phoneNumber = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phone) > 0;
    }
    
    
    
    public boolean checkPhoneExistsInLawyerTableExcludingId(String phone, Integer lawyerId) {
        String sql = "SELECT COUNT(*) FROM lawyerphone WHERE phoneNumber = ? AND lawyerId != ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, phone, lawyerId) > 0;
    }
    
    
}
