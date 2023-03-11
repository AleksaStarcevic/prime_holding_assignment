package com.example.internship_assignment.controllers;

import com.example.internship_assignment.data_transfer_objects.CreateNewTaskDTO;
import com.example.internship_assignment.data_transfer_objects.TaskAssessmentDTO;
import com.example.internship_assignment.data_transfer_objects.UpdateTaskDTO;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.exceptions.UserDoesNotExistException;
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


    @PostMapping("/tasks")
    public ResponseEntity<?> createNewTask(@Valid @RequestBody CreateNewTaskDTO taskDTO) throws UserDoesNotExistException {
        return new ResponseEntity<>(taskService.addNewTask(taskDTO), HttpStatus.CREATED);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTask(@PathVariable int id) throws TaskNotFoundException {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id,@Valid @RequestBody UpdateTaskDTO taskDTO) throws TaskNotFoundException, UserDoesNotExistException {
        return new ResponseEntity<>(taskService.updateTask(id,taskDTO), HttpStatus.OK);
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) throws TaskNotFoundException {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/employees/{id}/task/{taskId}/assessment")
    public ResponseEntity<?> taskAssessment(@PathVariable int id, @PathVariable int taskId,@Valid @RequestBody TaskAssessmentDTO taskAssessmentDTO) throws TaskNotFoundException {
        return new ResponseEntity<>(taskService.assessTask(id,taskId,taskAssessmentDTO), HttpStatus.OK);
    }


}
