package com.taskmaste.TaskMaste.DAO.impl;

import com.taskmaste.TaskMaste.DAO.interfaces.SignUpDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcSignUpDao implements SignUpDao {
    private static final Logger log = LoggerFactory.getLogger(JdbcSignUpDao.class);
    private final DataSource dataSource;

    @Autowired
    public JdbcSignUpDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean createUser(String username, String email, String password) {
        String sql = """
            INSERT INTO task_manager.users (username, email, password)
            VALUES (?, ?, ?);
            """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsAffected = preparedStatement.executeUpdate();
            log.info("User created successfully: {}", username);
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Database error: {}", e.getMessage());
            log.error("SQL State: {}", e.getSQLState());
            log.error("Error Code: {}", e.getErrorCode());
            return false;
        }
    }

    @Override
    public boolean isUserNameAvailable(String username) {
        String sql =
                """
                SELECT COUNT(*) FROM task_manager.users WHERE username = ?;
                """;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1) == 0;
            }
        }catch(SQLException e){
            log.error("Error checking username available.", e);
        }
        return false;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        String sql =
                """
                SELECT COUNT(*) FROM task_manager.users WHERE email = ?;
                """;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1) == 0;
            }
        }catch(SQLException e){
            log.error("Error checking email available.", e);
        }
        return false;
    }

    @Override
    public boolean validatePassword(String password, String confirmpassword) {
        return password != null &&
                password.equals(confirmpassword) &&
                password.length() >= 8;
    }

    @Override
    public boolean validateEmail(String email) {
        return false;
    }
}
