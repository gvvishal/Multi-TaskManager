package com.example.taskflow.Controller;

import com.example.taskflow.Dto.ProjectCreateRequest;
import com.example.taskflow.Dto.ProjectResponse;
import com.example.taskflow.Entity.Project;
import com.example.taskflow.Service.ProjectService;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectResponse createProject(@Valid @RequestBody ProjectCreateRequest request) {
        return projectService.createProject(request);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MANAGER')")
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PreAuthorize("hasAnyRole('MANGER','USER')")
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{projectId}/user/{userId}")
    public Project addUserProject(@PathVariable Long projectId,
                                  @PathVariable Long userId) {
        return projectService.addUserToProject(projectId, userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{projectId}/user/{userId}")
    public Project removeUserFromProject(@PathVariable Long projectId,
                                         @PathVariable Long userId) {
        return projectService.removeUserFromProject(projectId, userId);
    }
}
