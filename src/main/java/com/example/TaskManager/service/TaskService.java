package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Optional<Task> findId(Long id){
        return taskRepository.findById(id);
    }

    public Task create(Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task taskUpdate){
        return taskRepository.findById(id).map(existing -> {
            existing.setName(taskUpdate.getName());
            existing.setDescription(taskUpdate.getDescription());
            existing.setCompleted(taskUpdate.isCompleted());
            return taskRepository.save(existing);
        });
    }

    public boolean deleate(Long id){
        if(!taskRepository.existsById(id)){
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }
}
