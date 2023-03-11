package com.example.internship_assignment.data_transfer_objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class CreateNewTaskDTO {

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Future(message = "Due date cannot be past date")
    private Date dueDate;

    @Email(message = "Employee must have a valid email")
    private String employeeEmail;
}
