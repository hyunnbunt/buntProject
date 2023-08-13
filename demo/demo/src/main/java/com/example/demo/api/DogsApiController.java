package com.example.demo.api;

import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogsApiController {
    @Autowired
    DogService dogService;
    @GetMapping("dogs")
    public List<Dog> showDogs() {
        return dogService.showDogs();
    }

    @GetMapping("dogs/{id}")
    public Dog showDogProfile(@PathVariable Long id) {
        return dogService.showDogProfile(id);
    }

    @PostMapping("dogs")
    public Dog createDog(@RequestBody Dog dog) {

    }

}
