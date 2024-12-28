package com.taskmaste.TaskMaste.Models;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    private Date duedate;
    private boolean completionstatus;
    private String tasktype;

    public Task(String name, String description, Date duedate, boolean completionstatus, String tasktype) {
        this.name = name;
        this.description = description;
        this.duedate = duedate;
        this.completionstatus = completionstatus;
        this.tasktype = tasktype;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDuedate() {
        return duedate;
    }

    public boolean isCompletionstatus() {
        return completionstatus;
    }

    public String getTasktype() {
        return tasktype;
    }
}
