package com.taskmaste.TaskMaste.DAO.interfaces;

import com.taskmaste.TaskMaste.Models.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories(int userId);
    Category getCategoryByName(String categoryName, int userId);
    boolean createCategory(String name, int userId);
    boolean updateCategory(int categoryId, String name, int userId);
    boolean deleteCategory(int categoryId, int userId);
    boolean isCategoryNameAvailable(String name, int userId);
}
