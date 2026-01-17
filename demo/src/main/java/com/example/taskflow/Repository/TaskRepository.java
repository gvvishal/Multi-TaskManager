package com.example.taskflow.Repository;

import com.example.taskflow.Entity.Task;
import com.example.taskflow.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByAssignedUser(User user, Pageable pageable);
}
