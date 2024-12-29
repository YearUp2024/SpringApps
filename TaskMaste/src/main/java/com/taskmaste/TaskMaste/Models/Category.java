package com.taskmaste.TaskMaste.Models;

public class Category {
    private String name;
    private int userId;

    public Category(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

}
