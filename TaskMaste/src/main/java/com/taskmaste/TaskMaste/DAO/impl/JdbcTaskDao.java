package com.taskmaste.TaskMaste.DAO.impl;

import com.taskmaste.TaskMaste.DAO.interfaces.TaskDao;
import com.taskmaste.TaskMaste.Models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcTaskDao implements TaskDao {
    private static final Logger log = LoggerFactory.getLogger(JdbcTaskDao.class);
    private final DataSource dataSource;

    @Autowired
    public JdbcTaskDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Task> getAllTasks(int userId) {
        List<Task> tasks = new ArrayList<>();
        String sql =
                """
                SELECT title, description, due_date, completion_status, task_type
                FROM task_manager.tasks
                WHERE user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, userId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    tasks.add(new Task(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("due_date"),
                        resultSet.getBoolean("completion_status"),
                        resultSet.getString("task_type")
                    ));
                }
            }
        }catch(SQLException e){
            log.error("Error while trying to get All Tasks: {}", e.getMessage());
        }
        return tasks;
    }

    @Override
    public Task getTaskByTaskId(int taskId) {
        String sql =
                """
                SELECT title, description, due_date, completion_status, task_type
                FROM task_manager.tasks
                WHERE task_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, taskId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new Task(
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getDate("due_date"),
                            resultSet.getBoolean("completion_status"),
                            resultSet.getString("task_type")
                    );
                }
            }
        }catch(SQLException e){
            log.error("Error while trying to get Task by Task ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Task> getTasksByType(String taskType, int userId) {
        return List.of();
    }

    @Override
    public boolean createTask(String name, String description, Date dueDate, String taskType, int userId) {
        return false;
    }

    @Override
    public boolean updateTask(int taskId, String name, String description, Date dueDate, String taskType) {
        return false;
    }

    @Override
    public boolean updateTaskStatus(int taskId, boolean completionStatus) {
        return false;
    }

    @Override
    public boolean deleteTask(int taskId) {
        return false;
    }

    @Override
    public boolean isTaskNameAvailable(String name, int userId) {
        return false;
    }
}
