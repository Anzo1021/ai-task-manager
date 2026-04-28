package com.eanjali.ai_task_manager.repositiory;


import com.eanjali.ai_task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //in this it will give us save(), findall(), findbyid(), etc
    //no need to write whole sql here
}
