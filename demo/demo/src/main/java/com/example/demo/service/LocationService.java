package com.example.demo.service;


import com.example.demo.dto.NewLocationDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class LocationService {

    LocationRepository locationRepository;
    DogRepository dogRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository, DogRepository dogRepository) {
        this.locationRepository = locationRepository;
        this.dogRepository = dogRepository;
    }

    public Location dtoToEntity(NewLocationDto newLocationDto) {
        Location location = new Location();
        location.setId(newLocationDto.getId());
        location.setLatitude(newLocationDto.getLatitude());
        location.setLongitude(newLocationDto.getLongitude());
        Set<Dog> dogs = new HashSet();
        Dog dog = dogRepository.findById(newLocationDto.getDogId()).orElse(null);
        if (dog == null) {
            return null;
        }
        dogs.add(dog);
        location.setDogs(dogs);
        return location;
    }

    public Location createLocation(@RequestBody NewLocationDto newLocationDto) {
        Location location = dtoToEntity(newLocationDto);
        if (location == null || location.getId() != null) {
            return null;
        }
        return locationRepository.save(location);
    }

    public Location addWalkingDog(@RequestBody Long dogId) {

    }
}
