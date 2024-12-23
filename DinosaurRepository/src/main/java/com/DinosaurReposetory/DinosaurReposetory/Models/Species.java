package com.DinosaurReposetory.DinosaurReposetory.Models;

public class Species {
    private int id;
    private String name;
    private String period;
    private String dietType;
    private String description;

    public Species() {}

    public Species(int id, String name, String period, String dietType, String description) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.dietType = dietType;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPeriod() {
        return period;
    }

    public String getDietType() {
        return dietType;
    }

    public String getDescription() {
        return description;
    }
}
