package com.example.demo.api;

import com.example.demo.dto.LocationDto;
import com.example.demo.dto.LocationCreateDto;
import com.example.demo.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class LocationApiController {

    LocationService locationService;

    @Autowired
    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }


    /** Show the list of locations. */
    @GetMapping("locations")
    public List<LocationDto> showLocationsList() {
        return locationService.showLocationsList();
    }

    /** Show a location's detail. */
    @GetMapping("locations/{locationId}")
    public ResponseEntity<LocationDto> showLocationDetail(@PathVariable Long locationId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.showLocationsDetail(locationId));
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    /** Add a new walk location. */
    @PostMapping("locations")
    public ResponseEntity<LocationDto> createLocation(@RequestBody @Validated LocationCreateDto locationCreateDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.createLocation(locationCreateDto));
        } catch (EntityNotFoundException e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e2) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("locations/{locationId}")
    public ResponseEntity<LocationDto> deleteLocation(@PathVariable Long locationId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.deleteLocation(locationId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}