package com.example.demo.api;

import com.example.demo.dto.DogFriendsNameDto;
import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class DogFriendsApiController {
    DogService dogService;
    @Autowired
    public DogFriendsApiController(DogService dogService) {
        this.dogService = dogService;
    }

    /** Show all dog friends of a dog. */
    @GetMapping("dogs/{dogId}/friends")
    public ResponseEntity<List<DogFriendsNameDto>> showFriends(@PathVariable Long dogId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.dogService.showFriends(dogId));
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** Make friends for two dogs. */
    @PostMapping("dogs/{dogId}/make-new-friend")
    @Transactional
    public ResponseEntity<DogFriendsNameDto> makeFriends(@PathVariable Long dogId, @RequestBody Long friendId) {
        try {
           return ResponseEntity.status(HttpStatus.OK).body(dogService.makeFriends(dogId, friendId));
        } catch (NoSuchElementException | IllegalArgumentException e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("dogs/{dogId}/cancel-friend")
    @Transactional
    public ResponseEntity<DogFriendsNameDto> cancelFriends(@PathVariable Long dogId, @RequestBody Long friendId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dogService.cancelFriends(dogId, friendId));
        } catch (NoSuchElementException | IllegalArgumentException e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
