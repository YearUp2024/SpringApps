package com.taskmaste.TaskMaste.DAO.impl;

import com.taskmaste.TaskMaste.DAO.interfaces.CategoryDao;
import com.taskmaste.TaskMaste.Models.Category;
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
import java.util.List;

@Component
public class JdbcCategoryDao implements CategoryDao {
    private static final Logger log = LoggerFactory.getLogger(JdbcCategoryDao.class);
    private final DataSource dataSource;

    @Autowired
    public JdbcCategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAllCategories(int userId) {
        List<Category> categories = new ArrayList<>();
        String sql =
                """
                SELECT name, description, user_id
                FROM task_manager.categories
                WHERE user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, userId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Category category = new Category(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("user_id")
                    );
                    categories.add(category);
                }
            }
        }catch(SQLException e){
            log.error("Error getting All Categories: {}", e.getMessage());
        }
        return categories;
    }

    @Override
    public Category getCategoryByName(String categoryName, int userId) {
        String sql =
                """
                SELECT name, description, user_id
                FROM task_manager.categories
                WHERE name = ? AND user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, categoryName);
            preparedStatement.setInt(2, userId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Category category = new Category(
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("user_id")
                    );
                    return category;
                }
            }
        }catch(SQLException e){
            log.error("Error getting Category by Name: ", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean createCategory(String name, String description, int userId) {
        String sql =
                """
                INSERT INTO task_manager.categories (name, description, user_id)
                VALUES (?, ?, ?)
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            log.error("Error while Creating Category: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateCategory(int categoryId, String name, String description, int userId) {
        String sql =
                """
                UPDATE task_manager.categories
                SET name = ?, description = ?
                WHERE user_id = ? AND category_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, categoryId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            log.error("Error while Updating Category: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryId, int userId) {
        String sql =
                """
                DELETE FROM task_manager.categories
                WHERE category_id = ? AND user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            log.error("Error while Deleting Category: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isCategoryNameAvailable(String name, int userId) {
        String sql =
                """
                SELECT COUNT(*)
                FROM task_manager.categories
                WHERE name = ? AND user_id = ?;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0;
                }
            }
        }catch(SQLException e){
            log.error("Error while checking for name match: {}", e.getMessage());
        }
        return false;
    }
}
