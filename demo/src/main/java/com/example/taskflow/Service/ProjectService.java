package com.example.taskflow.Service;

import com.example.taskflow.Dto.ProjectCreateRequest;
import com.example.taskflow.Dto.ProjectResponse;
import com.example.taskflow.Entity.Project;

import java.util.List;

public interface  ProjectService {
    ProjectResponse createProject(ProjectCreateRequest request);
    Project addUserToProject(Long projectId,Long userId);
    Project removeUserFromProject(Long projectId,Long userd);
    List<ProjectResponse> getAllProjects();
    Project getProjectById(Long projectId);
}
