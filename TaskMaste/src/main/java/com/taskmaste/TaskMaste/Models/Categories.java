package com.taskmaste.TaskMaste.Models;

public class Categories {
    private String name;
    private String description;
    private int userId;

    public Categories(String name, String description, int userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
