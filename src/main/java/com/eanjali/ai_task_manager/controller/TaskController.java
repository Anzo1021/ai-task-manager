package com.eanjali.ai_task_manager.controller;

import com.eanjali.ai_task_manager.model.TaskRequest;
import com.eanjali.ai_task_manager.model.TaskResponse;
import com.eanjali.ai_task_manager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")  // allows frontend to call this API
public class TaskController {

    @Autowired
    private TaskService taskService;

    // POST /api/tasks  → create a new task
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.ok(response);
    }

    // GET /api/tasks  → get all tasks
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }


    // DELETE /api/tasks/{id}  → delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}