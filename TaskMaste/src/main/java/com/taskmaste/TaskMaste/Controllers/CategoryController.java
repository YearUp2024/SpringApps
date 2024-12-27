package com.taskmaste.TaskMaste.Controllers;

import com.taskmaste.TaskMaste.DAO.interfaces.CategoryDao;
import com.taskmaste.TaskMaste.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Category getCategoryByName(@PathVariable String name, @RequestParam int userId){
        return categoryDao.getCategoryByName(name, userId);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category){
       boolean categoryCreated = categoryDao.createCategory(category.getName(), category.getDescription(), category.getUserId());

       if(categoryCreated){
           return ResponseEntity.ok("Category Created Successfully!");
       }
       return ResponseEntity.badRequest().body("Category could not be created");
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable int categoryId , @RequestBody Category category){
        boolean categoryUpdated = categoryDao.updateCategory(categoryId, category.getName(), category.getDescription(), category.getUserId());

        if(categoryUpdated){
            return ResponseEntity.ok("Category Updated Successfully!");
        }
        return ResponseEntity.badRequest().body("Category Update Failed!");
    }
}
