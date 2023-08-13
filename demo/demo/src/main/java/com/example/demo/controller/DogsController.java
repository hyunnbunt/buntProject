package com.example.demo.controller;

import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class DogsController {
    @Autowired
    DogService dogService;

    @GetMapping("bunt-project/dogs")
    public String showDogs(Model model) {
        List<Dog> dogs = dogService.showDogs();
        model.addAttribute("dogs", dogs);
        return "dogs/showDogs";
    }
}
