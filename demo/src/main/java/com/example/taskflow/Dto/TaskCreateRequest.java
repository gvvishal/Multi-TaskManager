package com.example.taskflow.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3,max=100)
    private String title;
    @Size(max=255)
    private String description;
    @NotNull(message="Project Id is required")
    private Long projectId;
    @NotNull
    private Long assignedUserId;

}
