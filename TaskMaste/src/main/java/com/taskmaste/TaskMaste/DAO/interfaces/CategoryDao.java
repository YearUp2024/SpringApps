package com.taskmaste.TaskMaste.DAO.interfaces;

import com.taskmaste.TaskMaste.Models.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories(int userId);
    Category getCategoryById(int categoryId);
    boolean createCategory(String name, String description, int userId);
    boolean updateCategory(int categoryId, String name, String description);
    boolean deleteCategory(int categoryId);
    boolean isCategoryNameAvailable(String name, int userId);
}
