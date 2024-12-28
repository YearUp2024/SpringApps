package com.taskmaste.TaskMaste.Controllers;

import com.taskmaste.TaskMaste.DAO.interfaces.TaskDao;
import com.taskmaste.TaskMaste.Models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskDao taskDao;

    @Autowired
    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GetMapping("/{userId}")
    public List<Task> getAllTasks(@PathVariable int userId){
        return taskDao.getAllTasks(userId);
    }

    @GetMapping("/taskId/{taskId}")
    public ResponseEntity<Task> getTaskByTaskId(@PathVariable int taskId){
        Task taskByTaskId = taskDao.getTaskByTaskId(taskId);

        if(taskByTaskId != null){
            return ResponseEntity.ok(taskByTaskId);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{taskType}/{taskId}")
    public List<Task> getTaskByType(@PathVariable String taskType, @PathVariable int taskId){
        return taskDao.getTasksByType(taskType, taskId);
    }

    @PostMapping("/{categoryId}/{userId}")
    public ResponseEntity<String> isTaskCreated(@RequestBody Task task, @PathVariable int categoryId, @PathVariable int userId){
        boolean taskCreated = taskDao.createTask(
                task.getName(),
                task.getDescription(),
                task.getDuedate(),
                task.isCompletionstatus(),
                task.getTasktype(),
                categoryId,
                userId
        );

        if(taskCreated){
            return ResponseEntity.ok("Task is Created Successfully!");
        }
        return ResponseEntity.badRequest().body("Task was not able to be Created!");
    }

    @PutMapping("/{taskId}/{completionstatus}")
    public ResponseEntity<String> isTaskUpdated(@PathVariable int taskId, @PathVariable boolean completionstatus){
        boolean taskUpdated = taskDao.updateTaskStatus(taskId, completionstatus);

        if(taskUpdated){
            return ResponseEntity.ok("Task is Updated Successfully!");
        }
        return ResponseEntity.badRequest().body("Task is not able to be Updated!");
    }
}
