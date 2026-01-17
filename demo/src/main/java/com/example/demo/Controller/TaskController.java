package com.example.demo.Controller;

import com.example.demo.Entity.Task;
import com.example.demo.Entity.TaskStatus;
import com.example.demo.Service.TaskService;
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
    public Task create(@RequestParam String title,
                       @RequestParam(required = false) String description,
                       @RequestParam Long projectId,
                       @RequestParam Long assignedUserId) {
        return taskService.createTask(title, description, projectId, assignedUserId);
    }

    @PutMapping("/{taskId}/status")
    public Task updateTaskStatus(@PathVariable Long taskId,
                                 @RequestParam TaskStatus status) {
        return taskService.updateTaskStatus(taskId, status);
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public Task assignTask(@PathVariable Long taskId,
                           @PathVariable Long userId) {
        return taskService.assignTask(taskId, userId);
    }

    @GetMapping("/user/{userId}")
    public Page<Task> getTaskForUser(@PathVariable Long userId,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getTasksForUser(userId, pageable);
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

}
