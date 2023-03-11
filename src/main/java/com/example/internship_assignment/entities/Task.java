package com.example.internship_assignment.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private TaskAssessment taskAssessment;

    public Task(String title, String description, Date dueDate, Employee employee) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.employee = employee;
    }
}
