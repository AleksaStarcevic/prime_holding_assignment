package com.example.internship_assignment.repositories;

import com.example.internship_assignment.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
