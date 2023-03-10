package com.example.internship_assignment.repositories;

import com.example.internship_assignment.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    Optional<Task> findByIdAndEmployee_Id(int taskId,int employeeId);
}
