package com.example.demo.Service.impl;

import com.example.demo.Entity.Project;
import com.example.demo.Entity.Task;
import com.example.demo.Entity.TaskStatus;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.TaskService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ProjectRepository projectRepository,
                           UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task createTask(String title,
                           String description,
                           Long projectId,
                           Long assignedUserId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(assignedUserId)
                .orElseThrow(() -> new RuntimeException("USer not found"));
        Task task = Task.builder()
                .title(title)
                .description(description)
                .status(TaskStatus.TODO)
                .project(project)
                .assignedUser(user)
                .build();
        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskStatus(Long taskID, TaskStatus status) {
        Task task = taskRepository.findById(taskID)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    public Task assignTask(Long taskId, Long userID) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("USer not found"));

        task.setAssignedUser(user);
        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getTasksForUser(Long userId, Pageable pageable) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByAssignedUser(user, pageable);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }


}
