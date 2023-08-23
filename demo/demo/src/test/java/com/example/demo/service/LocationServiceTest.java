package com.example.demo.service;

import com.example.demo.dto.LocationCreateDto;
import com.example.demo.dto.LocationMembersDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import com.example.demo.entity.Owner;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationServiceTest {

    LocationService locationService;

    @Autowired
    public LocationServiceTest(LocationService locationService) {
        this.locationService = locationService;
    }

    @Test
    @Transactional
    void showLocationsList() {
        Location location1 = new Location(111.1D, 222.2D);
        Location location2 = new Location(222.2D, 111.1D);
        Owner owner1 = new Owner("Jiwook");
        Owner owner2 = new Owner("Kat");
        Dog dog1 = new Dog(owner1, "Hatu", 3D, 5.5, "male");
        Dog dog2 = new Dog(owner2, "Biscuit", 2D, 4.5, "male");
        List<Dog> dogs1 = new ArrayList<>();
        dogs1.add(dog1);
        dogs1.add(dog2);
        List<Dog> dogs2 = new ArrayList<>();
        location1.setWalkingDogs(dogs1);
        location2.setWalkingDogs(dogs2);
        List<Location> expected = new ArrayList<>(Arrays.asList(location1, location2));
        List<Location> actual = locationService.showLocationsList();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void createLocation() {
        Owner creatorOwner = new Owner("Jiwook");
        Dog creatorDog = new Dog(creatorOwner, "Hatu", 3D, 5.5, "male");
        Location expected = new Location(3L, 55.5, 66.6, List.of(creatorDog));

        LocationCreateDto locationCreateDto = new LocationCreateDto();
        locationCreateDto.setLatitude(55.5);
        locationCreateDto.setLongitude(66.6);
        locationCreateDto.setCreatorDogId(4L);
        locationService.createLocation(locationCreateDto);
        Location actual = locationService.showLocationsDetail(3L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void joinLocation() {
        List<Long> expected = new ArrayList<>();
        expected.add(1L);
        LocationMembersDto locationMembersDto = locationService.joinLocation(2L, 1L);
        List<Long> actual = locationMembersDto.getDogIds();
        assertEquals(expected.toString(), actual.toString());
    }
}