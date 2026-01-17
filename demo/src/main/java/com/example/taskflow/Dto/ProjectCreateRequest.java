package com.example.taskflow.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateRequest {
    @NotBlank(message = "Project name is required!!")
    @Size(min=3,max=100)
    private String name;
    @NotBlank(message = "Description is required")
    @Size(max = 255)
    private String description;


}
