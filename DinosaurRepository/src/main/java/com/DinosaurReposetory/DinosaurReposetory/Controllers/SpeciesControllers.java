package com.DinosaurReposetory.DinosaurReposetory.Controllers;

import com.DinosaurReposetory.DinosaurReposetory.DAO.Interfaces.SpeciesDao;
import com.DinosaurReposetory.DinosaurReposetory.Models.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dinosaurs")
public class SpeciesControllers {
    private SpeciesDao speciesDao;

    @Autowired
    public SpeciesControllers(SpeciesDao speciesDao) {
        this.speciesDao = speciesDao;
    }

    @GetMapping()
    public List<Species> getAllSpecies() {
        return speciesDao.getAllSpecies();
    }

    @GetMapping("/{species_name}")
    public Species getSpeciesByName(@PathVariable String species_name){
        return speciesDao.getSpeciesByName(species_name);
    }
}
