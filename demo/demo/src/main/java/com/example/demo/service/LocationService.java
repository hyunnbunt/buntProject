package com.example.demo.service;


import com.example.demo.dto.LocationListProfileDto;
import com.example.demo.dto.LocationMembersDto;
import com.example.demo.dto.LocationCreateDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LocationService {

    LocationRepository locationRepository;
    DogRepository dogRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository, DogRepository dogRepository) {
        this.locationRepository = locationRepository;
        this.dogRepository = dogRepository;
    }


    /** Show the list of locations. */
    public List<LocationListProfileDto> showLocationsList() {
        log.info(Integer.toString(locationRepository.findAll().size()));
        return locationRepository.findAll().stream().
                map(LocationListProfileDto::fromEntity).collect(Collectors.toList());
    }

    /** Show a detail of a location. */
    public LocationListProfileDto showLocationsDetail(Long locationId) throws NoSuchElementException {
        Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new NoSuchElementException("Can't find the location. Wrong id.")
        );
        return LocationListProfileDto.fromEntity(location);
    }

    @Transactional
    /** Add a new walk location of a dog. */
    public LocationCreateDto createLocation(@RequestBody LocationCreateDto locationCreateDto) throws NoSuchElementException, IllegalArgumentException {
        Location location = locationCreateDto.toEntity();
        if (location.getId() != null) {
            throw new IllegalArgumentException("Id must be null.");
        }
        Long creatorDogId = locationCreateDto.getCreatorDogId();
        if (creatorDogId == null) {
            throw new IllegalArgumentException("Can't find the dog. Id is null.");
        }
        Dog creatorDog = dogRepository.findById(creatorDogId).orElseThrow(
                () -> new IllegalArgumentException("Can't find the dog. Wrong id.")
        );
        if (!location.addWalkingDog(creatorDog)) {
            throw new IllegalArgumentException("Your dog already added this walking location.");
        }
        Location createdLocation = locationRepository.save(location);
        return LocationCreateDto.fromEntity(createdLocation, creatorDogId);
    }

    @Transactional
    public LocationMembersDto joinLocation(@PathVariable Long locationId, @RequestBody Long dogId) throws NoSuchElementException, IllegalArgumentException {
        Location targetLocation = locationRepository.findById(locationId).orElse(null);
        if (targetLocation == null) {
            throw new NoSuchElementException("Can't find the location. Wrong id.");
        }
        Dog dog = dogRepository.findById(dogId).orElse(null);
        if (dog == null) {
            throw new NoSuchElementException("Can't find the dog. Wrong id.");
        }
        if (!targetLocation.addWalkingDog(dog)) {
            throw new IllegalArgumentException("Your dog already added this walking location.");
        }
        Location updatedLocation = locationRepository.save(targetLocation);
        Set<Long> dogIds = updatedLocation.getWalkingDogs().stream().map(Dog::getId)
                .collect(Collectors.toSet());
        return LocationMembersDto.fromEntity(updatedLocation, dogIds);
    }


}
