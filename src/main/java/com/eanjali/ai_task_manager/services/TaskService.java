package com.eanjali.ai_task_manager.services;  // ✅ same package

import com.eanjali.ai_task_manager.model.Task;
import com.eanjali.ai_task_manager.model.TaskRequest;
import com.eanjali.ai_task_manager.model.TaskResponse;
import com.eanjali.ai_task_manager.repositiory.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AIService aiService;

    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task();
        task.setTaskTitle(request.getTaskTitle());

        Map<String, String> aiResult = aiService.analyzeTask(request.getTaskTitle());

        task.setAiSummary(aiResult.get("summary"));
        task.setPriority(aiResult.get("priority"));
        task.setCategory(aiResult.get("category"));

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTaskTitle(task.getTaskTitle());
        response.setAiSummary(task.getAiSummary());
        response.setPriority(task.getPriority());
        response.setCategory(task.getCategory());
        response.setCreatedAt(task.getCreatedAt());
        return response;
    }
}