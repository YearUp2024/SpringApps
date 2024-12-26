package com.taskmaste.TaskMaste.DAO.impl;

import com.taskmaste.TaskMaste.DAO.interfaces.LoginDao;
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
public class JdbcLoginDao implements LoginDao {
    private static final Logger log = LoggerFactory.getLogger(JdbcLoginDao.class);
    private DataSource dataSource;

    @Autowired
    public JdbcLoginDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean validateLogin(String username, String password) {
        String sql =
                """
                SELECT password
                FROM task_manager.users
                WHERE username = ?
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String storedPassword = resultSet.getString("password");
                return password.matches(storedPassword);
            }
        }catch(SQLException e){
            log.error("Error while logging in: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean checkUserExists(String username) {
        String sql =
                """
                SELECT username
                FROM task_manager.users
                WHERE username = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String storedUsername = resultSet.getString("username");
                return username.matches(storedUsername);
            }
        }catch(SQLException e){
            log.error("Error while logging in: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public String getStoredPassword(String password) {
        return "";
    }
}
