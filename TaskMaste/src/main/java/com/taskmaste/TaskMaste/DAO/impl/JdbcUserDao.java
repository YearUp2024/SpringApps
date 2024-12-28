package com.taskmaste.TaskMaste.DAO.impl;

import com.taskmaste.TaskMaste.DAO.interfaces.UserDao;
import com.taskmaste.TaskMaste.Models.Category;
import com.taskmaste.TaskMaste.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(JdbcUserDao.class);
    private final DataSource dataSource;

    @Autowired
    public JdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getUserByUserName(String username) {
        String sql =
                """
                SELECT *
                FROM task_manager.users
                WHERE username = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new User(
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                }
            }
        }catch(SQLException e){
            log.error("Error while getting User by Username: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean createUser(String username, String email, String password) {
        String sql =
                """
                INSERT INTO task_manager.users (username, email, password)
                VALUES (?, ?, ?);
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            return preparedStatement.executeUpdate() > 0;
        }catch(SQLException e){
            log.error("Error while Creating User: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateUser(String username, String email, String password, int userId) {
        String sql =
                """
                UPDATE task_manager.users
                SET username = ?, email = ?, password = ?
                WHERE user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, userId);

            return preparedStatement.executeUpdate() > 0;
        }catch(SQLException e){
            log.error("Error while Updating User: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        String sql =
                """
                DELETE FROM task_manager.users
                WHERE user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, userId);

            return preparedStatement.executeUpdate() > 0;
        }catch(SQLException e){
            log.error("Error while Delete User: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean validateUserCredentials(String username, String password) {
        String sql =
                """
                SELECT COUNT(*)
                FROM task_manager.users
                WHERE username = ? AND password = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt(1) > 0;
                }
            }
        }catch(SQLException e){
            log.error("Error while Validating User: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        String sql =
                """
                SELECT COUNT(*)
                FROM task_manager.users
                WHERE username = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt(1) == 0;
                }
            }
            return preparedStatement.executeUpdate() > 0;
        }catch(SQLException e){
            log.error("Error while Checking is Username Available: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return false;
    }
}
