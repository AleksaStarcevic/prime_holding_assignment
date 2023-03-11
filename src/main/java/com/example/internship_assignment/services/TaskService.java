package com.example.internship_assignment.services;

import com.example.internship_assignment.data_transfer_objects.CreateNewTaskDTO;
import com.example.internship_assignment.data_transfer_objects.TaskAssessmentDTO;
import com.example.internship_assignment.data_transfer_objects.UpdateTaskDTO;
import com.example.internship_assignment.entities.Task;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.exceptions.UserDoesNotExistException;

public interface TaskService {
    Task assessTask(int employeeId, int taskId, TaskAssessmentDTO taskAssessmentDTO) throws TaskNotFoundException;

    Task addNewTask(CreateNewTaskDTO taskDTO) throws UserDoesNotExistException;

    Task getTask(int id) throws TaskNotFoundException;

    Task updateTask(int id, UpdateTaskDTO taskDTO) throws TaskNotFoundException, UserDoesNotExistException;

    void deleteTask(int id) throws TaskNotFoundException;
}
