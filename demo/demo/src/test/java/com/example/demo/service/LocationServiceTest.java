package com.example.demo.service;

import com.example.demo.dto.LocationCreateDto;
import com.example.demo.dto.LocationDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import com.example.demo.entity.Owner;
import jakarta.transaction.Transactional;
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

//    @Test
//    @Transactional
//    void showLocationsList() {
//        LocationListProfileDto location1 = new LocationListProfileDto(1L, 111.1D, 222.2D);
//        LocationListProfileDto location2 = new LocationListProfileDto(2L, 222.2D, 111.1D);
//        List<LocationListProfileDto> expected = new ArrayList<>(Arrays.asList(location1, location2));
//        List<LocationListProfileDto> actual = locationService.showLocationsList();
//        assertEquals(expected.toString(), actual.toString());
//    }

    @Test
    @Transactional
    void createLocation() {
        Owner creatorOwner = new Owner("Jiwook");
        Dog creatorDog = new Dog(creatorOwner, "Hatu", 3D, 5.5, "male");
        Location expected = new Location(3L, 55.5, 66.6, Set.of(creatorDog));

        LocationCreateDto locationCreateDto = new LocationCreateDto();
        locationCreateDto.setLatitude(55.5);
        locationCreateDto.setLongitude(66.6);
        locationCreateDto.setCreatorDogId(4L);
        locationService.createLocation(locationCreateDto);
        LocationDto actual = locationService.showLocationsDetail(3L);
        assertEquals(expected.toString(), actual.toString());
    }

//    @Test
//    @Transactional
//    void joinLocation() {
//        List<Long> expected = new ArrayList<>();
//        expected.add(1L);
//        LocationDto locationDto = locationService.joinLocation(2L, 1L);
//        List<Long> actual = locationDto.getDogIds();
//        assertEquals(expected.toString(), actual.toString());
//    }
}