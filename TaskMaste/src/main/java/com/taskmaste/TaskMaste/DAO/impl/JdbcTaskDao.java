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
import java.sql.Date;
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
        List<Task> tasksByType = new ArrayList<>();
        String sql =
                """
                SELECT title, description, due_date, completion_status, task_type
                FROM task_manager.tasks
                WHERE task_type = ? AND user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, taskType);
            preparedStatement.setInt(2, userId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    tasksByType.add(new Task(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("due_date"),
                        resultSet.getBoolean("completion_status"),
                        resultSet.getString("task_type")
                    ));
                }
            }
        }catch(SQLException e){
            log.error("Error while trying to get Task by Task Type: {}", e.getMessage());
        }
        return tasksByType;
    }

    @Override
    public boolean createTask(String name, String description, Date duedate, boolean completionstatus, String taskType, int categoryId, int userId) {
        String sql = """
            INSERT INTO task_manager.tasks (title, description, due_date, completion_status, task_type, category_id, user_id)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDate(3, duedate);
            preparedStatement.setBoolean(4, completionstatus);
            preparedStatement.setString(5, taskType);
            preparedStatement.setInt(6, categoryId);
            preparedStatement.setInt(7, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error while trying to Create Task: {}", e.getMessage());
        }
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
