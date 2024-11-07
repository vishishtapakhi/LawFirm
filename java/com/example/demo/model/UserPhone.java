// package com.example.demo.model;

// import jakarta.persistence.EmbeddedId;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;

// @Data
// // @Entity
// @Table(name = "UserPhone")
// @EqualsAndHashCode
// @NoArgsConstructor // Add no-arg constructor
// public class UserPhone {

//     @EmbeddedId
//     private UserPhoneId id; // Composite key

//     @ManyToOne
//     @JoinColumn(name = "id", insertable = false, updatable = false)
//     private User user; // Reference to the User entity

//     // Constructor for convenience
//     public UserPhone(User user, String phoneNumber) {
//         this.user = user;
//         this.id = new UserPhoneId(user.getId(), phoneNumber);
//     }

//     // Optional: Override toString for easier debugging
//     @Override
//     public String toString() {
//         return "UserPhone{" +
//                 "userId=" + id.getUserId() +
//                 ", phoneNumber='" + id.getPhoneNumber() + '\'' +
//                 '}';
//     }

//     public Object getPhoneNumber() {
//         return null;
//     }
// }
