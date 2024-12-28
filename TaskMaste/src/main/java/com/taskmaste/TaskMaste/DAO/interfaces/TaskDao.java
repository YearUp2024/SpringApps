package com.taskmaste.TaskMaste.DAO.interfaces;

import com.taskmaste.TaskMaste.Models.Task;

import java.sql.Date;
import java.util.List;

public interface TaskDao {
    List<Task> getAllTasks(int userId);
    Task getTaskByTaskId(int taskId);
    List<Task> getTasksByType(String taskType, int userId);
    boolean createTask(String name, String description, Date duedate, boolean completionstatus, String taskType, int categoryId, int userId);
    boolean updateTaskStatus(int taskId, boolean completionstatus);
    boolean deleteTask(int taskId);
    boolean isTaskNameAvailable(String name, int userId);
}
