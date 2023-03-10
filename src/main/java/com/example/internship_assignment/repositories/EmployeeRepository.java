package com.example.internship_assignment.repositories;

import com.example.internship_assignment.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

    @Query(value = "select e.id,e.full_name,e.email,e.phone_number,e.birth_date,e.monthly_salary " +
            "from task t join employee e on (t.employee_id = e.id) " +
            "where month(t.due_date) = month(current_date - interval 1 month) and year(t.due_date) = year(current_date - interval 1 month) " +
            "GROUP BY t.employee_id " +
            "ORDER BY count(*) desc " +
            "LIMIT 5",nativeQuery = true)
    List<Employee> getTopFiveEmployees();

    @Query(value = "select e.id,e.full_name,e.email,e.birth_date,e.monthly_salary,e.phone_number " +
            "from task t join employee e on t.employee_id = e.id join task_assessment ta on t.grade_id = ta.id " +
            "where t.due_date > current_date - interval 6 month " +
            "group by t.employee_id " +
            "having avg(ta.grade) between 4 and 5;",nativeQuery = true)
    List<Employee> getEmployeesWithBestGrades();
}
