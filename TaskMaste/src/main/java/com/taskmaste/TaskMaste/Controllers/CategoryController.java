package com.taskmaste.TaskMaste.Controllers;

import com.taskmaste.TaskMaste.DAO.interfaces.CategoryDao;
import com.taskmaste.TaskMaste.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping
    public List<Category> getAllCategories(@RequestParam int userId) {
        return categoryDao.getAllCategories(userId);
    }

    @GetMapping("/{name}")
    public Category categoryByName(@PathVariable String name, @RequestParam int userId){
        return categoryDao.getCategoryByName(name, userId);
    }
}
