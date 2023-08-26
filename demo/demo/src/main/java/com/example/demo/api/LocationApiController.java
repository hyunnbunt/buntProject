package com.example.demo.api;

import com.example.demo.dto.LocationDeleteDto;
import com.example.demo.dto.LocationListProfileDto;
import com.example.demo.dto.LocationMembersDto;
import com.example.demo.dto.LocationCreateDto;
import com.example.demo.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<LocationListProfileDto> showLocationsList() {
        return locationService.showLocationsList();
    }

    /** Show a location's detail. */
    @GetMapping("locations/{locationId}")
    public ResponseEntity<LocationMembersDto> showLocationDetail(@PathVariable Long locationId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.showLocationsDetail(locationId));
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    /** Add a new walk location. */
    @PostMapping("locations")
    public ResponseEntity<LocationCreateDto> createLocation(@RequestBody LocationCreateDto locationCreateDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.createLocation(locationCreateDto));
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** Join in a location. Maximum capacity : 3. */


    /** Join in a location. Maximum capacity : 3. */
    @PatchMapping("locations/{locationId}")
    public  ResponseEntity<LocationMembersDto> updateLocation(@PathVariable Long locationId, @RequestBody LocationCreateDto locationCreateDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.updateLocation(locationId, locationCreateDto));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("locations/{locationId}")
    public ResponseEntity<LocationDeleteDto> deleteLocation(@PathVariable Long locationId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.deleteLocation(locationId));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}