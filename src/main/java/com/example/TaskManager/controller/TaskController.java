package com.example.TaskManager.controller;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskId(@PathVariable Long id) {
        return taskService.findId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> postTask(@Valid @RequestBody Task task) {
        Task taskSave = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskService.deleate(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
