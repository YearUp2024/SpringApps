package com.taskmaste.TaskMaste.Models;

public class Category {
    private String name;
    private String description;
    private int userId;

    public Category(String name, String description, int userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getUserId() {
        return userId;
    }

}
