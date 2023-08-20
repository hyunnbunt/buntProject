package com.example.demo.api;

import com.example.demo.dto.LocationMembersDto;
import com.example.demo.dto.LocationCreateDto;
import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationApiController {

    LocationService locationService;

    @Autowired
    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }


    /** Show the list of locations. */
    @GetMapping("locations")
    public ResponseEntity<List<Location>> showLocationsList() {
        List<Location> locationList = locationService.showLocationsList();
        return (locationList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(locationList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /** Show a location's detail. */
    @GetMapping("locations/{locationId}")
    public ResponseEntity<Location> showLocationDetail(@PathVariable Long locationId) {
        Location location = locationService.showLocationsDetail(locationId);
        return (location != null) ?
            ResponseEntity.status(HttpStatus.OK).body(location):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    /** Add a new walk location. */
    @PostMapping("locations")
    public ResponseEntity<LocationCreateDto> createLocation(@RequestBody LocationCreateDto locationCreateDto) {
        LocationCreateDto locationCreatedDto = locationService.createLocation(locationCreateDto);
        return (locationCreatedDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(locationCreatedDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /** Join in a location. Maximum capacity : 3. */
    @PatchMapping("locations/{locationId}")
    public  ResponseEntity<LocationMembersDto> joinLocation(@PathVariable Long locationId, @RequestBody Long dogId) {
        LocationMembersDto locationMembersDto = locationService.joinLocation(locationId, dogId);
        return (locationMembersDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(locationMembersDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
