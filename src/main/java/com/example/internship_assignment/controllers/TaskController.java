package com.example.internship_assignment.controllers;

import com.example.internship_assignment.data_transfer_objects.TaskAssessmentDTO;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/employees/{id}/task/{taskId}/assessment")
    public ResponseEntity<?> taskAssessment(@PathVariable int id, @PathVariable int taskId,@Valid @RequestBody TaskAssessmentDTO taskAssessmentDTO) throws TaskNotFoundException {
        return new ResponseEntity<>(taskService.assessTask(id,taskId,taskAssessmentDTO), HttpStatus.OK);
    }


}
