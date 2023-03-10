package com.example.internship_assignment.data_transfer_objects;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaskAssessmentDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Min(value = 1,message ="Min grade must be 1")
    @Max(value = 5,message ="Max grade must be 5")
    private int grade;

    @NotEmpty(message = "Comment cannot be empty")
    private String comment;
}
