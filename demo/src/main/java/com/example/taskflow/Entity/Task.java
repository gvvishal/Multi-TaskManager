package com.example.taskflow.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate dueDate;

    // Each task belongs to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;

    // Each task belongs to one project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
