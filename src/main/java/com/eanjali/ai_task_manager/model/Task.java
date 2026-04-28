package com.eanjali.ai_task_manager.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Data //lombok autogenerate no need to write getter and setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="task_title", nullable = false)
    private String taskTitle;

    @Column(name="ai_summary", columnDefinition = "TEXT")
    private String aiSummary;

    @Column(name="priority")
    private String priority;

    @Column(name="category")
    private String category;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt=LocalDateTime.now();
    }
}
