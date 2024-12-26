package com.taskmaste.TaskMaste.DAO.interfaces;

import java.util.List;

public interface Categories {
    List<Categories> getAllCategories(int userId);
    Categories getCategoryById(int categoryId);
    boolean createCategory(String name, String description, int userId);
    boolean updateCategory(int categoryId, String name, String description);
    boolean deleteCategory(int categoryId);
    boolean isCategoryNameAvailable(String name, int userId);
}
