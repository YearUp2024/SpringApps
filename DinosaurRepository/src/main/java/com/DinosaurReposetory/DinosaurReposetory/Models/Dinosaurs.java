package com.DinosaurReposetory.DinosaurReposetory.Models;

public class Dinosaurs {
    private int speciesId;
    private String speciesName;
    private String period;
    private String dietType;
    private String description;

    public Dinosaurs() {}

    public Dinosaurs(int speciesId, String speciesName, String period, String dietType, String description) {
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.period = period;
        this.dietType = dietType;
        this.description = description;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public String getSpeciesName() {
        return speciesName;
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
