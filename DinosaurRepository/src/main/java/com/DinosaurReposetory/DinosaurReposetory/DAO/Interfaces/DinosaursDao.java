package com.DinosaurReposetory.DinosaurReposetory.DAO.Interfaces;

import com.DinosaurReposetory.DinosaurReposetory.Models.Dinosaurs;

import java.util.List;

public interface DinosaursDao {
    List<Dinosaurs> getAllDinosaurs();
    Dinosaurs getDinosaursByName(String dinosaurName);
}
