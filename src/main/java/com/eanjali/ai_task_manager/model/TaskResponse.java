package com.eanjali.ai_task_manager.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String taskTitle;
    private String aiSummary;
    private String priority;
    private String category;
    private LocalDateTime createdAt;
}
