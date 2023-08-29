package com.example.demo.service;

import com.example.demo.dto.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DogServiceTest {
    DogService dogService;

    @Autowired
    public DogServiceTest(DogService dogService) {
        this.dogService = dogService;
    }

    @Test
    @Transactional
    void showDogs() {
//        Owner owner1 = new Owner("Junseok");
//        Owner owner2 = new Owner("Hyuna");
//        Owner owner3 = new Owner("Kat");
//        Owner owner4 = new Owner("Jiwook");
//        Owner owner5 = new Owner("Chih");

        DogDto a = new DogDto(1L, "Lana", 10d, 3D, "female");
        DogDto b = new DogDto(2L, "Bunt", 4D, 6.2D, "male");
        DogDto c = new DogDto(3L, "Biscuit", 2D, 4.5d, "male");
        DogDto d = new DogDto(4L, "Hatu", 3D, 5.5D, "male");
        DogDto e = new DogDto(5L, "Latte", 3D, 8D, "male");
        // LIst<DogProfileDto> 에 ArrayList<Dog> 을 어사인 했는데 왜 컴파일이 됐는지?
        List<DogDto> expected = new ArrayList(Arrays.asList(a, b, c, d, e));
        List<DogDto> actual = dogService.showDogs();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void showDogProfile() {
        DogDto expected = new DogDto(1L, "Lana", 10D, 3d, "female");
        DogDto actual = dogService.showDogProfile(1L);
        assertEquals(expected.toString(), actual.toString());
    }

//    @Test
//    @Transactional
//    void createDog() {
//        DogDto expected = new DogDto(6L, "Dallae", 2D, 3D, "female");
//        DogDto actual = dogService.createDog(expected);
//        assertEquals(expected.toString(), actual.toString());
//    }

//    @Test
//    @Transactional
//    void updateDog() {
//        DogUpdateDto expected = new DogUpdateDto(1L, "Bunt", 4D, 6.5D, "male");
//        DogUpdateDto actual = dogService.updateDog(1L, expected);
//        assertEquals(expected.toString(), actual.toString());
//    }
}
