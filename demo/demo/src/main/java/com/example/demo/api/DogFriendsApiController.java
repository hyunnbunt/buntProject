package com.example.demo.api;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogFriendDto;
import com.example.demo.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<List<DogDto>> showFriends(@PathVariable Long dogId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.dogService.showFriends(dogId));
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** Make friends for two dogs. */
    @PostMapping("dogs/{dogId}/make-new-friend")
    public ResponseEntity<DogDto> makeFriends(@PathVariable Long dogId, @RequestBody @Validated DogFriendDto dogFriendDto) {
        try {
           return ResponseEntity.status(HttpStatus.OK).body(dogService.makeFriends(dogId, dogFriendDto));
        } catch (NoSuchElementException e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e2) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("dogs/{dogId}/cancel-friend")
    public ResponseEntity<DogDto> cancelFriends(@PathVariable Long dogId, @RequestBody DogFriendDto dogFriendDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dogService.cancelFriends(dogId, dogFriendDto));
        } catch (NoSuchElementException e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e2) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
