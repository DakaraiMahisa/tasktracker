package com.task.tasktracker.service;

import com.task.tasktracker.entity.Task;
import com.task.tasktracker.exception.ResourceNotFoundException;
import com.task.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks(){
     List<Task> tasks = taskRepository.findAll();
     return tasks;
    }

    public Task createTask(Task task){
        if(task.getCompleted()==null)task.setCompleted(false);
        return taskRepository.save(task);
    }

   public Task toggleComplete(Long id){
       Task task = taskRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Task with ID "+id+" not found"));

       Boolean current =task.getCompleted()!=null?task.getCompleted():false;
       task.setCompleted(!current);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException( "Task with ID " + id + " not found");
        }
          taskRepository.deleteById(id);
    }
}
