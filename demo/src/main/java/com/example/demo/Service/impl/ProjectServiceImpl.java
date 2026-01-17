package com.example.demo.Service.impl;

import com.example.demo.Entity.Project;
import com.example.demo.Entity.User;
import com.example.demo.Service.ProjectService;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Project createProject(String name, String description) {
        Project project = Project.builder()
                .name(name)
                .description(description)
                .build();
        return projectRepository.save(project);
    }
    @Override
    public Project addUserToProject(Long projectId, Long userId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found!!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        project.getUsers().add(user);
        return projectRepository.save(project);
    }

    @Override
    public Project removeUserFromProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found!!"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!!"));
        project.getUsers().remove(user);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

}