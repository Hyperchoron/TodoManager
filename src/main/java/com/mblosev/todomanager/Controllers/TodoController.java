package com.mblosev.todomanager.Controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mblosev.todomanager.Controllers.Exceptions.TodoNotFoundException;
import com.mblosev.todomanager.Entities.TodoTask;
import com.mblosev.todomanager.Repositories.TodoTaskRepository;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class TodoController {
    private TodoTaskRepository todoTaskRepository;

    @GetMapping("/todos")
    public List<TodoTask> findAllTodoTasks() {
        return todoTaskRepository.findAll();
    }

    @GetMapping("/todos/currentday")
    public List<TodoTask> findAllCurrentDayTodoTasks(@RequestParam Optional<Boolean> filterCompleted) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        if (filterCompleted.isPresent())
            return todoTaskRepository.findByCreationDateGreaterThanEqualAndIsCompleted(calendar.getTime(),
                    filterCompleted.get());

        else {
            return todoTaskRepository.findByCreationDateGreaterThanEqual(calendar.getTime());
        }
    }

    @GetMapping("/todos/currentmonth")
    public List<TodoTask> findAllCurrentMonthTodoTasks(@RequestParam Optional<Boolean> filterCompleted) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 0);

        if (filterCompleted.isPresent())
            return todoTaskRepository.findByCreationDateGreaterThanEqualAndIsCompleted(calendar.getTime(),
                    filterCompleted.get());

        else {
            return todoTaskRepository.findByCreationDateGreaterThanEqual(calendar.getTime());
        }
    }

    @GetMapping("/todos/currentweek")
    public List<TodoTask> findAllCurrentWeekTodoTasks(@RequestParam Optional<Boolean> filterCompleted) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        if (filterCompleted.isPresent())
            return todoTaskRepository.findByCreationDateGreaterThanEqualAndIsCompleted(calendar.getTime(),
                    filterCompleted.get());

        else {
            return todoTaskRepository.findByCreationDateGreaterThanEqual(calendar.getTime());
        }
    }

    @GetMapping("/todos/{todoId}")
    public TodoTask getTodoTask(@PathVariable int todoId) {
        return todoTaskRepository.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException(todoId));
    }

    @PostMapping("/todos")
    public ResponseEntity<TodoTask> addTodoTask(@RequestBody TodoTask newTodo) {
        TodoTask savedTask = todoTaskRepository.save(newTodo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoTask> putTodoTask(@PathVariable int todoId, @RequestBody TodoTask todoTask) {
        return todoTaskRepository.findById(todoId).map((foundTodo) -> {
            foundTodo.setCreationDate(todoTask.getCreationDate());
            foundTodo.setCompleted(todoTask.isCompleted());
            foundTodo.setTask(todoTask.getTask());

            TodoTask savedTask = todoTaskRepository.save(foundTodo);

            return ResponseEntity.ok(savedTask);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/todos/{todoId}")
    public void patchTodoTaskCompleted(@PathVariable int todoId, boolean isCompleted) {
        todoTaskRepository.findById(todoId).ifPresent(foundTodo -> {
            foundTodo.setCompleted(isCompleted);

            var _ = todoTaskRepository.save(foundTodo);
        });
    }

    @DeleteMapping("/todos/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoTask(@PathVariable int todoId) {
        todoTaskRepository.findById(todoId).ifPresentOrElse(
                _ -> todoTaskRepository.deleteById(todoId),
                () -> {
                    throw new TodoNotFoundException(todoId);
                });
    }
}
