package com.example.TaskManager.controller;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    //private final TaskRepository taskRepository;
    private final TaskService taskService;

    public TaskController(/*TaskRepository taskRepository,*/ TaskService taskService) {
      //  this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        //return taskRepository.findAll();
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskId(@PathVariable Long id) {
        //return taskRepository.findById(id).map(task -> ResponseEntity.ok(task)).orElse(ResponseEntity.notFound().build());
        return taskService.findId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> postTask(@Valid @RequestBody Task task) {
        //Task taskSave = taskRepository.save(task);
        Task taskSave = taskService.create(task);
        //return ResponseEntity.status(HttpStatus.CREATED).body(taskSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        /*Task taskExiste = taskRepository.findById(id).orElse(null);
        if (taskExiste == null) {
            return ResponseEntity.notFound().build();
        }
        taskExiste.setName(task.getName());
        taskExiste.setDescription(task.getDescription());
        taskExiste.setCompleted(task.isCompleted());
        taskRepository.save(taskExiste);
        return ResponseEntity.ok(taskExiste); */
        return taskService.updateTask(id, task).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskService.deleate(id)) {
            return ResponseEntity.notFound().build();
        }
        //taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
