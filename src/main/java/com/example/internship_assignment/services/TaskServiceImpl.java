package com.example.internship_assignment.services;

import com.example.internship_assignment.data_transfer_objects.TaskAssessmentDTO;
import com.example.internship_assignment.entities.Task;
import com.example.internship_assignment.entities.TaskAssessment;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    @Override
    public Task assessTask(int employeeId, int taskId, TaskAssessmentDTO taskAssessmentDTO) throws TaskNotFoundException {
      Optional<Task> taskOptional = taskRepository.findByIdAndEmployee_Id(taskId,employeeId);
      if(taskOptional.isEmpty()) throw new TaskNotFoundException("There is no task for given employee");

      Task task = taskOptional.get();

      if(task.getTaskAssessment() != null) throw new TaskNotFoundException("This task is already assessed");
      task.setTaskAssessment(new TaskAssessment(taskAssessmentDTO.getName(),taskAssessmentDTO.getGrade(),taskAssessmentDTO.getComment()));
      return taskRepository.save(task);
    }
}
