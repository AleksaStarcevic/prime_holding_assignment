package com.example.internship_assignment.data_transfer_objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateTaskDTO {

    private String title;

    private String description;

    @Future(message = "Due date cannot be past date")
    private Date dueDate;

    @Email(message = "Employee must have a valid email")
    private String employeeEmail;
}
