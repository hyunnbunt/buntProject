package com.example.demo.api;

import com.example.demo.dto.*;
import com.example.demo.service.DogService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
public class DogApiController {
    private DogService dogService;
    /** DI using constructor. */
    @Autowired
    public DogApiController(DogService dogService) {
        this.dogService = dogService;
    }

    /** Show all dogs (nearby). */
    @GetMapping("dogs")
    public List<DogProfileDto> showDogs() {
        return dogService.showDogs();
    }

    /** Show a dog's profile. */
    @GetMapping("dogs/{id}")
    public ResponseEntity<DogProfileDto> showDogProfile(@PathVariable Long id) {
        try {
           return ResponseEntity.status(HttpStatus.OK).body(dogService.showDogProfile(id));
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** Register a new dog profile. */
    @PostMapping("dogs")
    public ResponseEntity<DogProfileDto> createDog(@RequestBody DogProfileDto dogProfileDto) {
       try {
           return ResponseEntity.status(HttpStatus.OK).body(dogService.createDog(dogProfileDto));
       } catch (IllegalArgumentException e) {
           log.info(e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    /** Update some basic fields of a dog's profile. Can't update organizing/participating events. */
    @PatchMapping("dogs/{id}")
    public ResponseEntity<DogUpdateDto> updateDog(@PathVariable Long id, @RequestBody DogUpdateDto dogUpdateDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dogService.updateDog(id, dogUpdateDto));
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    /** Delete a dog's profile. */
    @DeleteMapping("dogs/{id}")
    public ResponseEntity<DogProfileDto> deleteDog(@PathVariable Long id) {
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(dogService.deleteDog(id));
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** A dog joins an event. */
    @PatchMapping("dog-event-participation")
    public ResponseEntity<DogProfileDto> joinEvent(@RequestBody DogEventUpdateDto dogEventUpdateDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dogService.joinEvent(dogEventUpdateDto));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            log.info(e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("dog-event-cancellation")
    public ResponseEntity<DogProfileDto> cancelEvent(@RequestBody DogEventCancelDto dogEventCancelDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dogService.cancelEvent(dogEventCancelDto));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            log.info(e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("dog-location-registration")
    public  ResponseEntity<LocationMembersDto> joinLocation(@RequestBody DogLocationUpdateDto dogLocationUpdateDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dogService.joinLocation(dogLocationUpdateDto));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
