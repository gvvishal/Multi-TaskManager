package com.example.demo.Service;

import com.example.demo.Entity.Project;

import java.util.List;

public interface  ProjectService {
    Project createProject(String name, String description);
    Project addUserToProject(Long projectId,Long userId);
    Project removeUserFromProject(Long projectId,Long userd);
    List<Project> getAllProjects();
    Project getProjectById(Long projectId);
}
