package com.task.tasktracker.controller;

import com.task.tasktracker.entity.Task;
import com.task.tasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task newTask){
        if(newTask==null)throw new IllegalArgumentException("Task cannot be empty");
        Task  task = taskService.createTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/tasks/{id}/toggle")
    public ResponseEntity<Task> toggleTask(@PathVariable Long id){
        Task task = taskService.toggleComplete(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
         taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
