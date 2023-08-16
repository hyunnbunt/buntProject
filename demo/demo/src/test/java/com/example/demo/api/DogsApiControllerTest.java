package com.example.demo.api;

import com.example.demo.dto.DogProfileDto;
import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DogsApiControllerTest {

    @Autowired
    DogService dogService;

    @Test
    void showDogs() {
    }

    @Test
    void showDogProfile() {
        Dog a = new Dog();
        a.setName("Bunt");
        Dog b = new Dog();
        b.setName("Lana");
        Dog c = new Dog();
        c.setName("Buntu");
        Dog d = new Dog();
        d.setName("Hatu");

        // expected
        List<Dog> expectedDogs = new ArrayList(Arrays.asList(a, b, c, d));
        // actual
        List<DogProfileDto> dogs = dogService.showDogs();

        // compare
        assertEquals(expectedDogs.toString(), dogs.toString());

    }

    @Test
    void createDog() {
    }

    @Test
    void updateDog() {
    }
}