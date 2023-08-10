package com.example.demo.service;

import com.example.demo.entity.Dog;
import com.example.demo.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogsService {
    @Autowired
    DogRepository dogRepository;

    public List<Dog> showDogs() {
        return dogRepository.findAll();
    }
}
