package com.example.internship_assignment.data_transfer_objects;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class CreateNewEmployeeDTO {

    @NotEmpty(message = "Full name cannot be empty")
    private String fullName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;

    @Past(message = "Birthday cannot be future date")
    private Date birthDate;

    @Positive(message = "Salary must have a positive value")
    private double monthlySalary;
}
