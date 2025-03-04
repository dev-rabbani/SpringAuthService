package com.example.spring_auth_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname", nullable = false, length = 25)
    private String firstName;
    @Column(name = "lastname", length = 25)
    private String lastName;
    @Column(unique = true, nullable = false, length = 10)
    private String username;
    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
}
