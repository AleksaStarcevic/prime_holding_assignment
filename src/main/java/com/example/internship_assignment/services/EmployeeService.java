package com.example.internship_assignment.services;

import com.example.internship_assignment.data_transfer_objects.CreateNewEmployeeDTO;
import com.example.internship_assignment.data_transfer_objects.UpdateEmployeeDTO;
import com.example.internship_assignment.entities.Employee;
import com.example.internship_assignment.exceptions.EmployeeAlreadyExistsException;
import com.example.internship_assignment.exceptions.EmployeesDoNotMeetTheRequirementsForSalaryIncreaseException;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.exceptions.UserDoesNotExistException;

import java.util.List;


public interface EmployeeService {

    Employee addNewEmployee(CreateNewEmployeeDTO employeeDTO) throws EmployeeAlreadyExistsException;
    Employee getEmployee(int id) throws UserDoesNotExistException;

    Employee updateEmployee(int id, UpdateEmployeeDTO employeeDTO) throws UserDoesNotExistException, EmployeeAlreadyExistsException;

    void deleteEmployee(int id) throws UserDoesNotExistException;

    List<Employee> getTopFiveEmployees();

    List<Employee> increaseSalary() throws EmployeesDoNotMeetTheRequirementsForSalaryIncreaseException;

    double getAverageGradeForMonth(int id, int month);

    int getTasksPerMonthForEmployee(int id, int month);

    double getPercentOfGradeChangeComparedToLastYear(int id);

    Integer getTaskWithWorstGrade(int id, int month) throws TaskNotFoundException;
}
