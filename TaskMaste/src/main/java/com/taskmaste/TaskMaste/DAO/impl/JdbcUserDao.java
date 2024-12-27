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
    public User getUserById(int userId) {
        String sql =
                """
                SELECT *
                FROM task_manager.users
                WHERE user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, userId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                    );
                    return user;
                }
            }
        }catch(SQLException e){
            log.error("Error while getting User");
        }
        return null;
    }

    @Override
    public User getUserByUserName(String username) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }

    @Override
    public boolean createUser(String username, String email, String password) {
        return false;
    }

    @Override
    public boolean updateUser(String username, String email, String password) {
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }

    @Override
    public boolean validateUserCredentials(String username, String password) {
        return false;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return false;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return false;
    }
}
