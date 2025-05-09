package com.example.CRUDD.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.CRUDD.Model.Task;
import com.example.CRUDD.Repo.TaskRepo;

@Service
public class TaskService {

    @Autowired
    private TaskRepo repo;

    public ResponseEntity<Object> readbyid(int id) {
        Optional<Task> task = repo.findById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> Read() {
        List<Task> task = repo.findAll();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    public Task create(Task task) {
        return repo.save(task);
    }

    public Task update(Task task) {
        return repo.save(task);
    }

    public ResponseEntity<Object> delete(int ID) {
        if (repo.existsById(ID)) {
            repo.deleteById(ID);
            return new ResponseEntity<>("Task is deleted successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Task not Found",HttpStatus.NOT_FOUND);
        }
    }
}

