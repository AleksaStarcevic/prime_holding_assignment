package com.example.internship_assignment.controllers;

import com.example.internship_assignment.data_transfer_objects.CreateNewEmployeeDTO;
import com.example.internship_assignment.data_transfer_objects.UpdateEmployeeDTO;
import com.example.internship_assignment.exceptions.EmployeeAlreadyExistsException;
import com.example.internship_assignment.exceptions.EmployeesDoNotMeetTheRequirementsForSalaryIncreaseException;
import com.example.internship_assignment.exceptions.TaskNotFoundException;
import com.example.internship_assignment.exceptions.UserDoesNotExistException;
import com.example.internship_assignment.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<?> createNewEmployee(@Valid @RequestBody CreateNewEmployeeDTO employeeDTO) throws EmployeeAlreadyExistsException {
       return new ResponseEntity<>(employeeService.addNewEmployee(employeeDTO), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) throws UserDoesNotExistException {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id,@Valid @RequestBody UpdateEmployeeDTO employeeDTO) throws UserDoesNotExistException, EmployeeAlreadyExistsException {
       return new ResponseEntity<>(employeeService.updateEmployee(id,employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) throws UserDoesNotExistException {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/topEmployees")
    public ResponseEntity<?> topFiveEmployees() {
        return new ResponseEntity<>(employeeService.getTopFiveEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/salary")
    public ResponseEntity<?> increaseSalaryForEmployeesWithBestGradesInPastSixMonths() throws EmployeesDoNotMeetTheRequirementsForSalaryIncreaseException {
        return new ResponseEntity<>(employeeService.increaseSalary(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}/statistics/averageGradeMonth")
    public ResponseEntity<?> getAverageGradeForMonth(@PathVariable int id,@RequestParam int month) {
        return new ResponseEntity<>(employeeService.getAverageGradeForMonth(id,month), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}/statistics/tasksMonth")
    public ResponseEntity<?> getNumberOfTasksForMonth(@PathVariable int id,@RequestParam int month) {
        return new ResponseEntity<>(employeeService.getTasksPerMonthForEmployee(id,month), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}/statistics/gradeChange")
    public ResponseEntity<?> getPercentOfGradeChangeComparedToLastYear(@PathVariable int id) {
        return new ResponseEntity<>(employeeService.getPercentOfGradeChangeComparedToLastYear(id), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}/statistics/worstGradeTask")
    public ResponseEntity<?> getTaskWithWorstGrade(@PathVariable int id,@RequestParam int month) throws TaskNotFoundException {
        return new ResponseEntity<>(employeeService.getTaskWithWorstGrade(id,month), HttpStatus.OK);
    }
}
