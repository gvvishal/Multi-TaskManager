package com.example.taskflow.Controller;

import com.example.taskflow.Dto.TaskCreateRequest;
import com.example.taskflow.Dto.TaskResponse;
import com.example.taskflow.Dto.TaskStatusUpdateRequest;
import com.example.taskflow.Entity.Task;
import com.example.taskflow.Entity.TaskStatus;
import com.example.taskflow.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponse create(@Valid @RequestBody TaskCreateRequest request) {
        return taskService.createTask(request);
    }

    @PutMapping("/{taskId}/status")
    public TaskResponse updateTaskStatus(@PathVariable Long taskId,
                                 @Valid @RequestBody TaskStatusUpdateRequest request) {
        return taskService.updateTaskStatus(taskId,request);
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public Task assignTask(@PathVariable Long taskId,
                           @PathVariable Long userId) {
        return taskService.assignTask(taskId, userId);
    }

    @GetMapping("/user/{userId}")
    public Page<TaskResponse> getTasksForUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getTasksForUser(userId, pageable);
    }


    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

}
