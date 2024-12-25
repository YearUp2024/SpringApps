package com.taskmaste.TaskMaste.DAO.impl;

import com.taskmaste.TaskMaste.DAO.interfaces.SignUpDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            log.error("There was an error while creating your account", e);
            return false;
        }
    }

    @Override
    public boolean isUserNameAvailable(String username) {
        return false;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return false;
    }

    @Override
    public boolean validatePassword(String password, String confirmPassword) {
        return false;
    }

    @Override
    public boolean validateEmail(String email) {
        return false;
    }
}
