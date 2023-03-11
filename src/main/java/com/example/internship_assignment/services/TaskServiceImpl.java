package com.example.internship_assignment.services;

import com.example.internship_assignment.data_transfer_objects.CreateNewTaskDTO;
import com.example.internship_assignment.data_transfer_objects.TaskAssessmentDTO;
import com.example.internship_assignment.data_transfer_objects.UpdateTaskDTO;
import com.example.internship_assignment.entities.Employee;
import com.example.internship_assignment.entities.Task;
import com.example.internship_assignment.entities.TaskAssessment;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.exceptions.UserDoesNotExistException;
import com.example.internship_assignment.repositories.EmployeeRepository;
import com.example.internship_assignment.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public Task assessTask(int employeeId, int taskId, TaskAssessmentDTO taskAssessmentDTO) throws TaskNotFoundException {
      Optional<Task> taskOptional = taskRepository.findByIdAndEmployee_Id(taskId,employeeId);
      if(taskOptional.isEmpty()) throw new TaskNotFoundException("There is no task for given employee");

      Task task = taskOptional.get();
      if(task.getTaskAssessment() != null) throw new TaskNotFoundException("This task is already assessed");
      task.setTaskAssessment(new TaskAssessment(taskAssessmentDTO.getName(),taskAssessmentDTO.getGrade(),taskAssessmentDTO.getComment()));
      return taskRepository.save(task);
    }

    private Employee findEmployeeByEmail(String email) throws UserDoesNotExistException {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);
        if(employeeOptional.isEmpty()) throw new UserDoesNotExistException("User with given email does not exist");
        return employeeOptional.get();
    }

    @Override
    public Task addNewTask(CreateNewTaskDTO taskDTO) throws UserDoesNotExistException {
        Employee employee = findEmployeeByEmail(taskDTO.getEmployeeEmail());
        Task taskToSave = new Task(taskDTO.getTitle(),taskDTO.getDescription(),taskDTO.getDueDate(),employee);
        return taskRepository.save(taskToSave);
    }


    private Task findTaskById(int id) throws TaskNotFoundException {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isEmpty()) throw new TaskNotFoundException("Task with given id does not exist");
        return taskOptional.get();
    }
    @Override
    public Task getTask(int id) throws TaskNotFoundException {
        return findTaskById(id);
    }

    @Override
    public Task updateTask(int id, UpdateTaskDTO taskDTO) throws TaskNotFoundException, UserDoesNotExistException {
        Task task =  findTaskById(id);
        String email = taskDTO.getEmployeeEmail();

        if(email != null){
            Employee employee = findEmployeeByEmail(taskDTO.getEmployeeEmail());
            task.setEmployee(employee);
        }
        String title = taskDTO.getTitle();
        String description = taskDTO.getDescription();
        Date dueDate = taskDTO.getDueDate();

        if(title != null) task.setTitle(title);
        if(description != null) task.setDescription(description);
        if(dueDate != null) task.setDueDate(dueDate);

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int id) throws TaskNotFoundException {
       Task task = findTaskById(id);
       taskRepository.delete(task);
    }


}
