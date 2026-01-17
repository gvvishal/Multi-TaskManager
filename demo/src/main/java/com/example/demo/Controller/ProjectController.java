package com.example.demo.Controller;

import com.example.demo.Entity.Project;
import com.example.demo.Service.ProjectService;
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
    public Project createProject(@RequestParam String name,
                                 @RequestParam(required = false) String description) {
        return projectService.createProject(name, description);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/{projectId}/user/{userId}")
    public Project addUserProject(@PathVariable Long projectId,
                                  @PathVariable Long userId) {
        return projectService.addUserToProject(projectId, userId);
    }

    @DeleteMapping("/{projectId}/user/{userId}")
    public Project removeUserFromProject(@PathVariable Long projectId,
                                         @PathVariable Long userId) {
        return projectService.removeUserFromProject(projectId, userId);
    }
}
