package com.example.demo.service;

import com.example.demo.entity.Dog;
import com.example.demo.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DogService {
    @Autowired
    DogRepository dogRepository;

    public List<Dog> showDogs() {
        return dogRepository.findAll();
    }

    public Dog showDogProfile(@PathVariable Long id) {
        return dogRepository.findById(id).orElse(null);
    }
}
