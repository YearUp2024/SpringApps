package com.taskmaste.TaskMaste.DAO.interfaces;

import com.taskmaste.TaskMaste.Models.Task;

import java.util.Date;
import java.util.List;

public interface TaskDao {
    List<Task> getAllTasks(int userId);
    Task getTaskById(int taskId);
    List<Task> getTasksByType(String taskType, int userId);
    boolean createTask(String name, String description, Date dueDate, String taskType, int userId);
    boolean updateTask(int taskId, String name, String description, Date dueDate, String taskType);
    boolean updateTaskStatus(int taskId, boolean completionStatus);
    boolean deleteTask(int taskId);
    boolean isTaskNameAvailable(String name, int userId);
}
