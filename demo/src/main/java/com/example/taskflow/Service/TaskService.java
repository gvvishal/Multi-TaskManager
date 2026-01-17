package com.example.taskflow.Service;

import com.example.taskflow.Dto.TaskCreateRequest;
import com.example.taskflow.Dto.TaskResponse;
import com.example.taskflow.Dto.TaskStatusUpdateRequest;
import com.example.taskflow.Entity.Task;
import com.example.taskflow.Entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TaskService {

    TaskResponse createTask(TaskCreateRequest request);
    TaskResponse updateTaskStatus(Long taskId, TaskStatusUpdateRequest userId);
    Task assignTask(Long taskId,Long userId);
    Page<TaskResponse> getTasksForUser(Long userId, Pageable pageable);
    Task getTaskById(Long taskId);



}
