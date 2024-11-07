// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.time.LocalDate;

// @Entity
// @Data
// @NoArgsConstructor
// @Table(name = "Lawyer")
// public class Lawyer {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int lawyerID;

//     @Column(name = "FName", length = 50, nullable = false)
//     private String fname;

//     @Column(name = "MName", length = 50)
//     private String mname;

//     @Column(name = "LName", length = 50, nullable = false)
//     private String lname;

//     @Column(name = "DateOfBirth", nullable = false)
//     private LocalDate dob;

//     @Column(name = "Qualification", length = 100)
//     private String qualification;

//     @Column(name = "Experience")
//     private int experience;

//     @Column(name = "Positions", length = 50)
//     private String position;

//     @Column(name = "Expertise", length = 50)
//     private String expertise;

//     @Column(name = "PhoneNumber", length = 20)
//     private String phoneNumber;

//     @Column(name = "Email", length = 100)
//     private String email;

//     public boolean isApproved() {
//         return false;
//     }
// }
package com.example.demo.model;

import java.sql.Date;

public class Lawyer {
    private int lawyerID;
    private String fName;
    private String mName;
    private String lName;
    private Date dateOfBirth;
    private String qualification;
    private int experience;
    private String expertise;
    private String positions;

    // Getters and Setters
    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
}
