package com.example.demo.api;

import com.example.demo.dto.DogFriendsNameDto;
import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DogFriendsApiController {
    DogService dogService;
    @Autowired
    public DogFriendsApiController(DogService dogService) {
        this.dogService = dogService;
    }

    /** Show all dog friends of a dog. */
    @GetMapping("dogs/{dogId}/friends")
    public List<DogFriendsNameDto> showFriends(@PathVariable Long dogId) {
        List<DogFriendsNameDto> dogFriends =
                dogService.showFriends(dogId).stream()
                .map(dogFriend -> DogFriendsNameDto.fromEntity(dogFriend))
                .collect(Collectors.toList());
        return dogFriends;
    }

    /** Make friends for two dogs. */
    @PostMapping("dogs/{dogId}/makeNewFriend")
    public ResponseEntity<DogFriendsNameDto> makeFriends(@PathVariable Long dogId, @RequestBody Long friendsId) {
        DogFriendsNameDto newFriend = dogService.makeFriends(dogId, friendsId);
        return (!(newFriend == null)) ?
                ResponseEntity.status(HttpStatus.OK).body(newFriend):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
