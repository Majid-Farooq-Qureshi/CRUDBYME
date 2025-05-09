package com.example.CRUDD.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDD.Model.Task;
import com.example.CRUDD.Repo.TaskRepo;
import com.example.CRUDD.Service.TaskService;

@RestController
@CrossOrigin(origins = "*")
public class TaskController{

    @Autowired TaskService service;
    @Autowired
    private TaskRepo repo;

    @GetMapping("/tasks/{ID}")
    public ResponseEntity<Object> Readbyid(@PathVariable int ID){
        return service.readbyid(ID);
    }

    @GetMapping("/tasks")
    public ResponseEntity<Object> Read(){
        return service.Read();
    }

   @PostMapping("/tasks")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task createdTask = service.create(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        Optional<Task> existingTask = repo.findById(id);
    
    if (existingTask.isPresent()) {
        task.setID(id); // Ensure the same ID is used
        Task updatedTask = repo.save(task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    @DeleteMapping("/tasks/{ID}")
    public ResponseEntity<Object> delete(@PathVariable int ID){
        return service.delete(ID);
    }

}