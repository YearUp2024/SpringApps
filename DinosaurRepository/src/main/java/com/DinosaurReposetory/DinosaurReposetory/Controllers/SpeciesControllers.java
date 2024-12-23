package com.DinosaurReposetory.DinosaurReposetory.Controllers;

import com.DinosaurReposetory.DinosaurReposetory.DAO.Interfaces.SpeciesDao;
import com.DinosaurReposetory.DinosaurReposetory.Models.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SpeciesControllers {
    private SpeciesDao speciesDao;

    @Autowired
    public SpeciesControllers(SpeciesDao speciesDao) {
        this.speciesDao = speciesDao;
    }

    @GetMapping("/dinosaurs")
    public List<Species> getAllDinosaurs() {
        return speciesDao.getAllSpecies();
    }
}
