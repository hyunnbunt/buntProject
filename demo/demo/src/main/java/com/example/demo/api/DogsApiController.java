package com.example.demo.api;

import com.example.demo.dto.DogEventUpdateDto;
import com.example.demo.dto.DogProfileDto;
import com.example.demo.dto.DogUpdateDto;
import com.example.demo.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DogsApiController {
    private DogService dogService;
    /** DI using constructor. */
    @Autowired
    public DogsApiController(DogService dogService) {
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
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** Register a new dog profile. */
    @PostMapping("dogs")
    public ResponseEntity<DogProfileDto> createDog(@RequestBody DogProfileDto dogProfileDto) {
       DogProfileDto createdDogProfileDto = dogService.createDog(dogProfileDto);
        // createDog method in DogService would return null if the id's given in the request body.
       return (createdDogProfileDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createdDogProfileDto):
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /** Update some basic fields of a dog's profile. Can't update organizing/participating events. */
    @PatchMapping("dogs/{id}")
    public ResponseEntity<DogUpdateDto> updateDog(@PathVariable Long id, @RequestBody DogUpdateDto dogUpdateDto) {
        DogUpdateDto updatedDogDto = dogService.updateDog(id, dogUpdateDto);
        // updateDog method in DogService would return null if the given @PathVariable id's wrong or different from @RequestBody id.
        return (updatedDogDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(updatedDogDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /** A dog joins an event. */
    @PatchMapping("dog-event-participation")
    public ResponseEntity<DogProfileDto> joinEvent(@RequestBody DogEventUpdateDto dogEventUpdateDto) {
        DogProfileDto eventUpdatedDogDto = dogService.joinEvent(dogEventUpdateDto);
        return (eventUpdatedDogDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(eventUpdatedDogDto):
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("dogs/{id}")
    public ResponseEntity<DogProfileDto> deleteDog(@PathVariable Long id) {
        DogProfileDto deletedDogProfileDto = dogService.deleteDog(id);
        return (deletedDogProfileDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(deletedDogProfileDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
