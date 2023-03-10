package com.example.internship_assignment.services;

import com.example.internship_assignment.data_transfer_objects.TaskAssessmentDTO;
import com.example.internship_assignment.entities.Task;
import com.example.internship_assignment.exceptions.TaskNotFoundException;

public interface TaskService {
    Task assessTask(int employeeId, int taskId, TaskAssessmentDTO taskAssessmentDTO) throws TaskNotFoundException;
}
