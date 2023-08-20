package com.example.demo.api;

import com.example.demo.dto.DogFriendsNameDto;
import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    
}
