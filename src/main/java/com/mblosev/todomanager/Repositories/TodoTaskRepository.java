package com.mblosev.todomanager.Repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.mblosev.todomanager.Entities.TodoTask;
import java.util.List;
import java.util.Date;


public interface TodoTaskRepository extends ListCrudRepository<TodoTask, Integer> {
    List<TodoTask> findByCreationDateGreaterThanEqual(Date creationDate);

    List<TodoTask> findByCreationDateGreaterThanEqualAndIsCompleted(Date creationDate, boolean isCompleted); 
}
