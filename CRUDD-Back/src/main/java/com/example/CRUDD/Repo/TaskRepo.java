package com.example.CRUDD.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.CRUDD.Model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {
    
}
