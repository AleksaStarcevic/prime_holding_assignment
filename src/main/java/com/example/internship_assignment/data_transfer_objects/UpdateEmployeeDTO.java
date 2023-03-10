package com.example.internship_assignment.data_transfer_objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UpdateEmployeeDTO {


//    private Optional<String> fullName;
//
//    private Optional<String> email;
//
//    private Optional<String> phoneNumber;
//
//    private Optional<Date> birthDate;
//
//    private Optional<Double> monthlySalary;

    private String fullName;

    @Email(message = "Email must be valid")
    private String email;

    private String phoneNumber;

    @Past(message = "Birthday cannot be future date")
    private Date birthDate;

    private Double monthlySalary;

}
