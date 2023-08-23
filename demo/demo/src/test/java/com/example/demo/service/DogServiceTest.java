package com.example.demo.service;

import com.example.demo.dto.DogEventUpdateDto;
import com.example.demo.dto.DogFriendsNameDto;
import com.example.demo.dto.OwnersDogProfileDto;
import com.example.demo.dto.DogUpdateDto;
import com.example.demo.entity.Event;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
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

        OwnersDogProfileDto a = new OwnersDogProfileDto(1L, "Lana", 10d, 3D, "female");
        OwnersDogProfileDto b = new OwnersDogProfileDto( 2L, "Bunt", 4D, 6.2D, "male");
        OwnersDogProfileDto c = new OwnersDogProfileDto(3L, "Biscuit",  2D, 4.5d,"male");
        OwnersDogProfileDto d = new OwnersDogProfileDto(4L, "Hatu", 3D,5.5D,  "male");
        OwnersDogProfileDto e = new OwnersDogProfileDto(5L, "Latte", 3D,8D,  "male");
        // LIst<DogProfileDto> 에 ArrayList<Dog> 을 어사인 했는데 왜 컴파일이 됐는지?
        List<OwnersDogProfileDto> expected = new ArrayList(Arrays.asList(a, b, c, d, e));
        List<OwnersDogProfileDto> actual = dogService.showDogs();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void showDogProfile() {
        OwnersDogProfileDto expected = new OwnersDogProfileDto(1L, "Lana",10D, 3d, "female");
        OwnersDogProfileDto actual = dogService.showDogProfile(1L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void createDog() {
        OwnersDogProfileDto expected = new OwnersDogProfileDto(6L, "Dallae", 2D, 3D, "female");
        OwnersDogProfileDto actual = dogService.createDog(expected);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void updateDog() {
        DogUpdateDto expected = new DogUpdateDto(1L, "Bunt", 4D, 6.5D, "male");
        DogUpdateDto actual = dogService.updateDog(1L, expected);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void deleteDog() {
        Event event = new Event();
        event.setId(3L);
        event.setDate(20231101L);
        event.setTime(2050L);
        event.setLatitude(11.11D);
        event.setLongitude(55.16D);
        OwnersDogProfileDto expected = new OwnersDogProfileDto(4L, 4L, "Hatu", 3D, 5.5D, "male", Lists.newArrayList(event));
        OwnersDogProfileDto actual = dogService.deleteDog(4L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void joinEvent() {
        OwnersDogProfileDto expected = new OwnersDogProfileDto(5L, "Latte", 3D, 8.0D, "male");
        OwnersDogProfileDto actual = dogService.joinEvent(new DogEventUpdateDto(5L, 3L));
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void showFriends() {
        List<DogFriendsNameDto> expected = new ArrayList(Arrays.asList(new DogFriendsNameDto("Lana")));
        List<DogFriendsNameDto> actual = dogService.showFriends(2L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void makeFriends() {
        List<DogFriendsNameDto> expected1 = new ArrayList(Arrays.asList(new DogFriendsNameDto("Lana"), new DogFriendsNameDto("Latte")));
        List<DogFriendsNameDto> expected2 = new ArrayList(Arrays.asList(new DogFriendsNameDto("Bunt")));
        dogService.makeFriends(2L, 5L);
        List<DogFriendsNameDto> actual1 = dogService.showFriends(2L);
        List<DogFriendsNameDto> actual2 = dogService.showFriends(5L);
        assertEquals(expected1.toString(), actual1.toString());
        assertEquals(expected2.toString(), actual2.toString());
    }

    @Test
    @Transactional
    void cancelFriends() {
        dogService.cancelFriends(1L, 2L);
        List<DogFriendsNameDto> actual1 = dogService.showFriends(1L);
        List<DogFriendsNameDto> actual2 = dogService.showFriends(2L);
        assertTrue(actual1.isEmpty());
        assertTrue(actual2.isEmpty());
    }
}