package com.DinosaurReposetory.DinosaurReposetory.Controllers;

import com.DinosaurReposetory.DinosaurReposetory.DAO.Interfaces.DinosaursDao;
import com.DinosaurReposetory.DinosaurReposetory.Models.Dinosaurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dinosaurs")
public class DinosaursControllers {
    private DinosaursDao dinosaursDao;

    @Autowired
    public DinosaursControllers(DinosaursDao dinosaursDao) {
        this.dinosaursDao = dinosaursDao;
    }

    @GetMapping()
    public List<Dinosaurs> getAllDinosaurs(){
        return dinosaursDao.getAllDinosaurs();
    }
}
