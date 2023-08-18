package com.example.demo.api;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationApiController {

    LocationService locationService;

    @Autowired
    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }

//    /** Add a new walk location. */
//    @PostMapping("location")
//    public ResponseEntity<Location> newLocation(@RequestBody LocationDto locationDto) {
//        Location newLocation = locationService.newLocation(locationDto);
//        return (newLocation != null) ?
//            ResponseEntity.status(HttpStatus.OK).body(newLocation):
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

}
