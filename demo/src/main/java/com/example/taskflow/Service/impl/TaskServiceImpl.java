package com.example.taskflow.Service.impl;

import com.example.taskflow.Dto.TaskCreateRequest;
import com.example.taskflow.Dto.TaskResponse;
import com.example.taskflow.Dto.TaskStatusUpdateRequest;
import com.example.taskflow.Entity.Project;
import com.example.taskflow.Entity.Task;
import com.example.taskflow.Entity.TaskStatus;
import com.example.taskflow.Entity.User;
import com.example.taskflow.Repository.ProjectRepository;
import com.example.taskflow.Repository.TaskRepository;
import com.example.taskflow.Repository.UserRepository;
import com.example.taskflow.Service.TaskService;

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
    public TaskResponse createTask(TaskCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(request.getAssignedUserId())
                .orElseThrow(() -> new RuntimeException("USer not found"));
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.TODO);
        task.setProject(project);
        task.setAssignedUser(user);
        Task saved = taskRepository.save(task);
        return mapToResponse(saved);

    }

    @Override
    public TaskResponse updateTaskStatus(Long taskID, TaskStatusUpdateRequest request) {
        Task task = taskRepository.findById(taskID)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(request.getStatus());
        return mapToResponse(taskRepository.save(task));
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
    public Page<TaskResponse> getTasksForUser(Long userId, Pageable pageable) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByAssignedUser(user, pageable).map(this::mapToResponse);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

private TaskResponse mapToResponse(Task task){
        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setProjectId(task.getProject().getId());
        response.setAssignedUSerId(task.getAssignedUser().getId());
        return response;

}
}
