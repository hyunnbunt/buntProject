package com.example.demo.api;

import com.example.demo.dto.NewLocationDto;
import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationApiController {

    LocationService locationService;

    @Autowired
    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }

    /** Add a new walk location of a dog. */
    @PostMapping("locations")
    public ResponseEntity<Location> createLocation(@RequestBody NewLocationDto newLocationDto) {
        Location newLocation = locationService.createLocation(newLocationDto);
        return (newLocation != null) ?
            ResponseEntity.status(HttpStatus.OK).body(newLocation):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("locations/{locationId}")
    public Location addWalkingDog(@PathVariable Long locationId, @RequestBody Long dogId) {
        Location
    }

}
