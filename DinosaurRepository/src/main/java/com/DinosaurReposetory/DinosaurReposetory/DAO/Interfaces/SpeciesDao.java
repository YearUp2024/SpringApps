package com.DinosaurReposetory.DinosaurReposetory.DAO.Interfaces;

import com.DinosaurReposetory.DinosaurReposetory.Models.Species;

import java.util.List;

public interface SpeciesDao {
    List<Species> getAllSpecies();
    Species getSpeciesByName(String speciesName);
}
