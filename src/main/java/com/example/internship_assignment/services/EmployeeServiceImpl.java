package com.example.internship_assignment.services;

import com.example.internship_assignment.data_transfer_objects.CreateNewEmployeeDTO;
import com.example.internship_assignment.data_transfer_objects.UpdateEmployeeDTO;
import com.example.internship_assignment.entities.Employee;
import com.example.internship_assignment.exceptions.EmployeeAlreadyExistsException;
import com.example.internship_assignment.exceptions.EmployeesDoNotMeetTheRequirementsForSalaryIncreaseException;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.exceptions.UserDoesNotExistException;
import com.example.internship_assignment.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee addNewEmployee(CreateNewEmployeeDTO employeeDTO) throws EmployeeAlreadyExistsException {
        Employee employeeToSave = new Employee(employeeDTO.getFullName(),employeeDTO.getEmail(),employeeDTO.getPhoneNumber(),employeeDTO.getBirthDate(),employeeDTO.getMonthlySalary());
       Optional<Employee> employeeOptional = employeeRepository.findByEmail(employeeDTO.getEmail());

       if(employeeOptional.isPresent()) throw new EmployeeAlreadyExistsException("Employee with given email already exists");
       return employeeRepository.save(employeeToSave);
    }

    @Override
    public Employee getEmployee(int id) throws UserDoesNotExistException {
        return findEmployeeById(id);
    }

    @Override
    public Employee updateEmployee(int id, UpdateEmployeeDTO employeeDTO) throws UserDoesNotExistException, EmployeeAlreadyExistsException {
        Employee employee = findEmployeeById(id);

        String emailToUpdate = employeeDTO.getEmail();
        String fullNameToUpdate = employeeDTO.getFullName();
        String phoneToUpdate = employeeDTO.getPhoneNumber();
        Date birthDateToUpdate = employeeDTO.getBirthDate();
        Double salaryToUpdate = employeeDTO.getMonthlySalary();

        Optional<Employee> employeeOptional = employeeRepository.findByEmail(emailToUpdate);
        if(employeeOptional.isPresent()) throw new EmployeeAlreadyExistsException("This email is not available");

        if(emailToUpdate != null) employee.setEmail(emailToUpdate);
        if(fullNameToUpdate != null) employee.setFullName(fullNameToUpdate);
        if(phoneToUpdate != null) employee.setPhoneNumber(phoneToUpdate);
        if(birthDateToUpdate != null) employee.setBirthDate(birthDateToUpdate);
        if(salaryToUpdate != null && salaryToUpdate > 0) employee.setMonthlySalary(salaryToUpdate);

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) throws UserDoesNotExistException {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getTopFiveEmployees() {
      List<Employee> employees = employeeRepository.getTopFiveEmployees();
      return employees;
    }

    @Override
    public List<Employee> increaseSalary() throws EmployeesDoNotMeetTheRequirementsForSalaryIncreaseException {
       List<Employee> employeesToIncreaseSalary = employeeRepository.getEmployeesWithBestGrades();
       if(employeesToIncreaseSalary.isEmpty()) throw new EmployeesDoNotMeetTheRequirementsForSalaryIncreaseException("None of the employees met the criteria for salary increase");

       return employeesToIncreaseSalary.stream().map(employee -> {
            employee.setMonthlySalary(employee.getMonthlySalary() * 1.2);
            return employeeRepository.save(employee);
       }).collect(Collectors.toList());
    }

    @Override
    public double getAverageGradeForMonth(int employeeId, int month) {
       Optional<Double> gradeOptional =  employeeRepository.getAverageGradeForMonth(employeeId,month);
       if(gradeOptional.isPresent()){
           return gradeOptional.get();
       }
       return 0;
    }

    @Override
    public int getTasksPerMonthForEmployee(int id, int month) {
        Optional<Integer> tasksOptional = employeeRepository.getTasksPerMonthForEmployee(id,month);
       if(tasksOptional.isPresent()){
           return tasksOptional.get();
       }
       return 0;
    }

    @Override
    public double getPercentOfGradeChangeComparedToLastYear(int id) {
       Optional<Double> previousYearAvgGradeOptional = employeeRepository.getPercentOfGradeChangeComparedToLastYear(id, Year.now().getValue() - 1);
       Optional<Double> thisYearAvgGradeOptional = employeeRepository.getPercentOfGradeChangeComparedToLastYear(id, Year.now().getValue());

       if(previousYearAvgGradeOptional.isPresent() && thisYearAvgGradeOptional.isPresent()){
           Double thisYearAverageGrade = thisYearAvgGradeOptional.get();
           Double lastYearAverageGrade = previousYearAvgGradeOptional.get();
           return Math.round((thisYearAverageGrade - lastYearAverageGrade) / lastYearAverageGrade * 100);
       }
       return 0;
    }

    @Override
    public Integer getTaskWithWorstGrade(int id, int month) throws TaskNotFoundException {
       Optional<Integer> taskOptional =  employeeRepository.getTaskWithWorstGradeForEmployee(id,month);
       if(taskOptional.isEmpty()) throw new TaskNotFoundException("Task with minimum grade for employee not found");
       return taskOptional.get();
    }

    private Employee findEmployeeById(int id) throws UserDoesNotExistException {
        Optional<Employee> employeeOptional =  employeeRepository.findById(id);
        if(employeeOptional.isEmpty()) throw new UserDoesNotExistException("Can't find user with given id");
        return employeeOptional.get();
    }
}
