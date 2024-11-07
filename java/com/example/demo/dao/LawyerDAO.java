// package com.example.demo.dao;

// import com.example.demo.model.Lawyer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// @Repository
// public class LawyerDAO {

//     @Autowired
//     private JdbcTemplate jdbcTemplate;

//     public void saveLawyer(Lawyer lawyer) {
//         String query = "INSERT INTO Lawyer (FName, MName, LName, DateOfBirth, Qualification, Experience, Expertise, Positions) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//         jdbcTemplate.update(query, lawyer.getFName(), lawyer.getMName(), lawyer.getLName(), lawyer.getDateOfBirth(), lawyer.getQualification(), lawyer.getExperience(), lawyer.getExpertise(), lawyer.getPositions());
//     }

//     public Integer getLastInsertId() {
//         return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
//     }

//     public void saveLawyerPhone(Integer lawyerId, String phoneNumber) {
//         String query = "INSERT INTO LawyerPhone (LawyerID, PhoneNumber) VALUES (?, ?)";
//         jdbcTemplate.update(query, lawyerId, phoneNumber);
//     }

//     public void saveLawyerEmail(Integer lawyerId, String email) {
//         String query = "INSERT INTO LawyerEmail (LawyerID, EmailAddress) VALUES (?, ?)";
//         jdbcTemplate.update(query, lawyerId, email);
//     }

//     public List<Lawyer> listLawyers() {
//         return jdbcTemplate.query("SELECT * FROM Lawyer", new BeanPropertyRowMapper<>(Lawyer.class));
//     }

//     public Lawyer getLawyerById(Integer id) {
//         return jdbcTemplate.queryForObject(
//             "SELECT * FROM Lawyer WHERE LawyerID = ?",
//             new BeanPropertyRowMapper<>(Lawyer.class),
//             id
//         );
//     }

//     public List<String> getLawyerPhones(Integer lawyerId) {
//         return jdbcTemplate.query(
//             "SELECT PhoneNumber FROM LawyerPhone WHERE LawyerID = ?",
//             (rs, rowNum) -> rs.getString("phoneNumber"),
//             lawyerId
//         );
//     }

//     public List<String> getLawyerEmails(Integer lawyerId) {
//         return jdbcTemplate.query(
//             "SELECT EmailAddress FROM LawyerEmail WHERE LawyerID = ?",
//             (rs, rowNum) -> rs.getString("emailAddress"),
//             lawyerId
//         );
//     }

//     public void updateLawyer(Lawyer lawyer) {
//         String query = "UPDATE Lawyer SET FName = ?, MName = ?, LName = ?, DateOfBirth = ?, Qualification = ?, Experience = ?, Expertise = ?, Positions = ? WHERE LawyerID = ?";
//         jdbcTemplate.update(query, lawyer.getFName(), lawyer.getMName(), lawyer.getLName(), lawyer.getDateOfBirth(), lawyer.getQualification(), lawyer.getExperience(), lawyer.getExpertise(), lawyer.getPositions(), lawyer.getLawyerID());
//     }

//     public void deleteLawyer(Integer id) {
//         jdbcTemplate.update("DELETE FROM Lawyer WHERE LawyerID = ?", id);
//     }

//     public void deleteLawyerPhone(Integer lawyerId) {
//         jdbcTemplate.update("DELETE FROM LawyerPhone WHERE LawyerID = ?", lawyerId);
//     }

//     public void deleteLawyerEmail(Integer lawyerId) {
//         jdbcTemplate.update("DELETE FROM LawyerEmail WHERE LawyerID = ?", lawyerId);
//     }

//     public void updateLawyerPhones(Integer lawyerId, List<String> newPhoneNumbers) {
//         String deleteQuery = "DELETE FROM LawyerPhone WHERE LawyerID = ?";
//         jdbcTemplate.update(deleteQuery, lawyerId);

//         String insertQuery = "INSERT INTO LawyerPhone (LawyerID, PhoneNumber) VALUES (?, ?)";
//         for (String phoneNumber : newPhoneNumbers) {
//             if (phoneNumber != null && !phoneNumber.isEmpty()) {
//                 jdbcTemplate.update(insertQuery, lawyerId, phoneNumber);
//             }
//         }
//     }

//     public void updateLawyerEmails(Integer lawyerId, List<String> newEmails) {
//         String deleteQuery = "DELETE FROM LawyerEmail WHERE LawyerID = ?";
//         jdbcTemplate.update(deleteQuery, lawyerId);

//         String insertQuery = "INSERT INTO LawyerEmail (LawyerID, EmailAddress) VALUES (?, ?)";
//         for (String email : newEmails) {
//             if (email != null && !email.isEmpty()) {
//                 jdbcTemplate.update(insertQuery, lawyerId, email);
//             }
//         }
//     }

//     public List<Lawyer> getLawyerByEmail(String email) {
//         // First, query the LawyerEmail table to get the LawyerID for the given email address
//         String emailQuery = "SELECT LawyerID FROM LawyerEmail WHERE EmailAddress = ?";
        
//         List<Integer> lawyerIds = jdbcTemplate.query(emailQuery, (rs, rowNum) -> rs.getInt("LawyerID"), email);
    
//         if (lawyerIds.isEmpty()) {
//             return new ArrayList<>(); // Return an empty list if no LawyerID is found
//         }
    
//         // Then, query the Lawyer table using the retrieved LawyerID(s)
//         String lawyerQuery = "SELECT * FROM Lawyer WHERE LawyerID = ?";
        
//         List<Lawyer> lawyers = new ArrayList<>();
//         for (Integer lawyerId : lawyerIds) {
//             lawyers.add(jdbcTemplate.queryForObject(lawyerQuery, new BeanPropertyRowMapper<>(Lawyer.class), lawyerId));
//         }
    
//         return lawyers;
//     }
    
//     // New method to fetch lawyer by email
//     // public List<Lawyer> getLawyerByEmail(String email) {
//     //     String sql = "SELECT * FROM Lawyer WHERE EmailAddress = ?";
//     //     return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Lawyer.class), email);
//     // }

//     // // New method to fetch assigned cases for a lawyer
//     // public List<Map<String, Object>> fetchAssignedCases(int lawyerId) {
//     //     String sql = "SELECT c.CorporateCaseID, c.CaseDesc " +
//     //                  "FROM CorporateCase c " +
//     //                  "JOIN TaskLawyerCorp tl ON c.CorporateCaseID = tl.CorporateCaseID " +
//     //                  "WHERE tl.LawyerID = ?";
//     //     return jdbcTemplate.queryForList(sql, lawyerId);
//     // }

//     // // New method to fetch assigned tasks for a lawyer
//     // public List<Map<String, Object>> fetchAssignedTasks(int lawyerId) {
//     //     String sql = "SELECT t.TaskID, t.TaskDesc, t.Status " +
//     //                  "FROM TaskCorp t " +
//     //                  "JOIN TaskLawyerCorp tl ON t.TaskID = tl.TaskID " +
//     //                  "WHERE tl.LawyerID = ?";
//     //     return jdbcTemplate.queryForList(sql, lawyerId);
//     // }

//     public List<Lawyer> searchLawyers(String keyword) {
//         String query = "SELECT * FROM Lawyer WHERE FName LIKE ? OR LName LIKE ? OR Specialization LIKE ?";
//         String searchKeyword = "%" + keyword + "%"; // Add wildcards for partial matching
//         return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Lawyer.class), searchKeyword, searchKeyword, searchKeyword);
//     }
    
//     public int countLawyers() {
//         String query = "SELECT COUNT(*) FROM Lawyer";
//         Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        
//         // If the result is null, return 0 to handle potential null values safely
//         return count != null ? count : 0;
//     }

//     public boolean emailExistsInLawyerTable(String email) {
//         String query = "SELECT COUNT(*) FROM LawyerEmail WHERE EmailAddress = ?";
//         Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
//         return count != null && count > 0;
//     }
    
    
    
// }
package com.example.demo.dao;

import com.example.demo.model.Lawyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LawyerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // public void saveLawyer(Lawyer lawyer) {
    //     // Insert the Lawyer and get the generated LawyerID
    //     String query = "INSERT INTO Lawyer (FName, MName, LName, DateOfBirth, Qualification, Experience, Expertise, Positions) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    //     jdbcTemplate.update(query, lawyer.getFName(), lawyer.getMName(), lawyer.getLName(), lawyer.getDateOfBirth(), lawyer.getQualification(), lawyer.getExperience(), lawyer.getExpertise(), lawyer.getPositions());
        
    //     // Retrieve the last inserted LawyerID
    //     Integer lawyerId = getLastInsertId();
        
    //     // Now, save the Lawyer's phone numbers and emails using the correct LawyerID
    //     for (String phoneNumber : lawyer.getPhoneNumbers()) {
    //         saveLawyerPhone(lawyerId, phoneNumber);
    //     }
    //     for (String email : lawyer.getEmails()) {
    //         saveLawyerEmail(lawyerId, email);
    //     }
    // }
    
    // public Integer getLastInsertId() {
    //     return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    // }
    

    public void saveLawyer(Lawyer lawyer) {
        String query = "INSERT INTO Lawyer (FName, MName, LName, DateOfBirth, Qualification, Experience, Expertise, Positions) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, lawyer.getFName(), lawyer.getMName(), lawyer.getLName(), lawyer.getDateOfBirth(), lawyer.getQualification(), lawyer.getExperience(), lawyer.getExpertise(), lawyer.getPositions());
    }

    public Integer getLastInsertId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public void saveLawyerPhone(Integer lawyerId, String phoneNumber) {
        String query = "INSERT INTO LawyerPhone (LawyerID, PhoneNumber) VALUES (?, ?)";
        jdbcTemplate.update(query, lawyerId, phoneNumber);
    }

    public void saveLawyerEmail(Integer lawyerId, String email) {
        String query = "INSERT INTO LawyerEmail (LawyerID, EmailAddress) VALUES (?, ?)";
        jdbcTemplate.update(query, lawyerId, email);
    }

    public List<Lawyer> listLawyers() {
        return jdbcTemplate.query("SELECT * FROM Lawyer", new BeanPropertyRowMapper<>(Lawyer.class));
    }

    public Lawyer getLawyerById(Integer id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM Lawyer WHERE LawyerID = ?",
            new BeanPropertyRowMapper<>(Lawyer.class),
            id
        );
    }

    public List<String> getLawyerPhones(Integer lawyerId) {
        return jdbcTemplate.query(
            "SELECT PhoneNumber FROM LawyerPhone WHERE LawyerID = ?",
            (rs, rowNum) -> rs.getString("PhoneNumber"),
            lawyerId
        );
    }

    public List<String> getLawyerEmails(Integer lawyerId) {
        return jdbcTemplate.query(
            "SELECT EmailAddress FROM LawyerEmail WHERE LawyerID = ?",
            (rs, rowNum) -> rs.getString("EmailAddress"),
            lawyerId
        );
    }

    public void updateLawyer(Lawyer lawyer) {
        String query = "UPDATE Lawyer SET FName = ?, MName = ?, LName = ?, DateOfBirth = ?, Qualification = ?, Experience = ?, Expertise = ?, Positions = ? WHERE LawyerID = ?";
        jdbcTemplate.update(query, lawyer.getFName(), lawyer.getMName(), lawyer.getLName(), lawyer.getDateOfBirth(), lawyer.getQualification(), lawyer.getExperience(), lawyer.getExpertise(), lawyer.getPositions(), lawyer.getLawyerID());
    }

    public void deleteLawyer(Integer id) {
        jdbcTemplate.update("DELETE FROM Lawyer WHERE LawyerID = ?", id);
    }

    public void deleteLawyerPhone(Integer lawyerId) {
        jdbcTemplate.update("DELETE FROM LawyerPhone WHERE LawyerID = ?", lawyerId);
    }

    public void deleteLawyerEmail(Integer lawyerId) {
        jdbcTemplate.update("DELETE FROM LawyerEmail WHERE LawyerID = ?", lawyerId);
    }

    public void updateLawyerPhones(Integer lawyerId, List<String> newPhoneNumbers) {
        // Delete existing phone numbers
        String deleteQuery = "DELETE FROM LawyerPhone WHERE LawyerID = ?";
        jdbcTemplate.update(deleteQuery, lawyerId);

        // Insert new phone numbers
        String insertQuery = "INSERT INTO LawyerPhone (LawyerID, PhoneNumber) VALUES (?, ?)";
        for (String phoneNumber : newPhoneNumbers) {
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                jdbcTemplate.update(insertQuery, lawyerId, phoneNumber);
            }
        }
    }

    public void updateLawyerEmails(Integer lawyerId, List<String> newEmails) {
        // Delete existing emails
        String deleteQuery = "DELETE FROM LawyerEmail WHERE LawyerID = ?";
        jdbcTemplate.update(deleteQuery, lawyerId);

        // Insert new emails
        String insertQuery = "INSERT INTO LawyerEmail (LawyerID, EmailAddress) VALUES (?, ?)";
        for (String email : newEmails) {
            if (email != null && !email.isEmpty()) {
                jdbcTemplate.update(insertQuery, lawyerId, email);
            }
        }
    }

    public List<Lawyer> searchLawyers(String keyword) {
        String query = "SELECT * FROM Lawyer WHERE FName LIKE ? OR LName LIKE ? OR Expertise LIKE ?";
        String searchKeyword = "%" + keyword + "%"; // Add wildcards for partial matching
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Lawyer.class), searchKeyword, searchKeyword, searchKeyword);
    }

    public int countLawyers() {
        String query = "SELECT COUNT(*) FROM Lawyer";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);

        // If the result is null, return 0 to handle potential null values safely
        return count != null ? count : 0;
    }

    public boolean emailExistsInLawyerTable(String email) {
        String query = "SELECT COUNT(*) FROM LawyerEmail WHERE EmailAddress = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
        return count != null && count > 0;
    }

    // New method to fetch lawyer by email
    public List<Lawyer> getLawyerByEmail(String email) {
        String emailQuery = "SELECT LawyerID FROM LawyerEmail WHERE EmailAddress = ?";
        List<Integer> lawyerIds = jdbcTemplate.query(emailQuery, (rs, rowNum) -> rs.getInt("LawyerID"), email);

        if (lawyerIds.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if no LawyerID is found
        }

        // Query the Lawyer table using the retrieved LawyerID(s)
        String lawyerQuery = "SELECT * FROM Lawyer WHERE LawyerID = ?";
        List<Lawyer> lawyers = new ArrayList<>();
        for (Integer lawyerId : lawyerIds) {
            lawyers.add(jdbcTemplate.queryForObject(lawyerQuery, new BeanPropertyRowMapper<>(Lawyer.class), lawyerId));
        }

        return lawyers;
    }
}
