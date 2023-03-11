package com.example.internship_assignment.data_transfer_objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateEmployeeDTO {


    private String fullName;

    @Email(message = "Email must be valid")
    private String email;

    private String phoneNumber;

    @Past(message = "Birthday cannot be future date")
    private Date birthDate;

    private Double monthlySalary;

}
