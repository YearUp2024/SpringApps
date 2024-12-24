package com.DinosaurReposetory.DinosaurReposetory.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<String> index(@RequestParam(defaultValue = "World") String country){
        List<String> urls = new ArrayList<>();
        urls.add("Dinosaurs: http://localhost:8080/dinosaurs");
        urls.add("Dinosaurs: http://localhost:8080/dinosaurs/dinosaurName");
        urls.add("Dinosaurs: http://localhost:8080/species");
        urls.add("Dinosaurs: http://localhost:8080/species/speciesName");

        return urls;
    }
}
