package com.example.internship_assignment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private double monthlySalary;

    public Employee(String fullName, String email, String phoneNumber, Date birthDate, double monthlySalary) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.monthlySalary = monthlySalary;
    }
}
