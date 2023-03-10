package com.example.internship_assignment.services;

import com.example.internship_assignment.data_transfer_objects.CreateNewEmployeeDTO;
import com.example.internship_assignment.data_transfer_objects.UpdateEmployeeDTO;
import com.example.internship_assignment.entities.Employee;
import com.example.internship_assignment.exceptions.EmployeeAlreadyExistsException;
import com.example.internship_assignment.exceptions.UserDoesNotExistException;
import com.example.internship_assignment.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Employee updateEmployee(int id, UpdateEmployeeDTO employeeDTO) throws UserDoesNotExistException {
        Employee employee = findEmployeeById(id);

        String emailToUpdate = employeeDTO.getEmail();
        String fullNameToUpdate = employeeDTO.getFullName();
        String phoneToUpdate = employeeDTO.getPhoneNumber();
        Date birthDateToUpdate = employeeDTO.getBirthDate();
        Double salaryToUpdate = employeeDTO.getMonthlySalary();

        if(emailToUpdate != null) employee.setEmail(emailToUpdate);
        if(fullNameToUpdate != null) employee.setFullName(fullNameToUpdate);
        if(phoneToUpdate != null) employee.setPhoneNumber(phoneToUpdate);
        if(birthDateToUpdate != null) employee.setBirthDate(birthDateToUpdate);
        if(salaryToUpdate != null && salaryToUpdate >0) employee.setMonthlySalary(salaryToUpdate);

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

    private Employee findEmployeeById(int id) throws UserDoesNotExistException {
        Optional<Employee> employeeOptional =  employeeRepository.findById(id);
        if(employeeOptional.isEmpty()) throw new UserDoesNotExistException("Can't find user with given id");
        Employee employee = employeeOptional.get();
        return employee;
    }
}
