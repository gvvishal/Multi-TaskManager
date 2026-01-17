package com.example.demo.Service;

import com.example.demo.Entity.Task;
import com.example.demo.Entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TaskService {

    Task createTask(String title, String description, Long projectId, Long assignedUserId);
    Task updateTaskStatus(Long taskId, TaskStatus userId);
    Task assignTask(Long taskId,Long userId);
    Page<Task> getTasksForUser(Long userId, Pageable pageable);
    Task getTaskById(Long taskId);



}
