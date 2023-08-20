package com.example.demo.service;


import com.example.demo.dto.LocationMembersDto;
import com.example.demo.dto.LocationCreateDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
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
    public List<Location> showLocationsList() {
        return locationRepository.findAll();
    }

    /** Show a detail of a location. */
    public Location showLocationsDetail(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location == null) {
            return null;
        }
        return location;
    }

    /** Add a new walk location of a dog. */
    public LocationCreateDto createLocation(@RequestBody LocationCreateDto locationCreateDto) {
        Location location = locationCreateDto.toEntity();
        if (location.getId() != null) {
            return null;
        }
        Long creatorDogId = locationCreateDto.getCreatorDogId();
        Dog creatorDog = dogRepository.findById(creatorDogId).orElse(null);
        if (creatorDog == null) {
            return null;
        }
        if (!location.addWalkingDog(creatorDog)) {
            return null;
        }
        Location createdLocation = locationRepository.save(location);
        return LocationCreateDto.fromEntity(createdLocation, creatorDogId);
    }

    public LocationMembersDto joinLocation(@PathVariable Long locationId, @RequestBody Long dogId) {
        Location targetLocation = locationRepository.findById(locationId).orElse(null);
        if (targetLocation == null) {
            return null;
        }
        Dog dog = dogRepository.findById(dogId).orElse(null);
        if (dog == null) {
            return null;
        }
        if (!targetLocation.addWalkingDog(dog)) {
            return null;
        }
        Location updatedLocation = locationRepository.save(targetLocation);
        Set<Long> dogIds = updatedLocation.getWalkingDogs().stream().map(eachDog -> eachDog.getId())
                .collect(Collectors.toSet());
        return LocationMembersDto.fromEntity(updatedLocation, dogIds);
    }


}
