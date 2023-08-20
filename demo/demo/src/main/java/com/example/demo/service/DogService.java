package com.example.demo.service;

import com.example.demo.dto.DogEventUpdateDto;
import com.example.demo.dto.DogFriendsNameDto;
import com.example.demo.dto.DogProfileDto;
import com.example.demo.dto.DogUpdateDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DogService {

    private DogRepository dogRepository;
    OwnerService ownerService;
    private EventRepository eventRepository;


    @Autowired
    public DogService(DogRepository dogRepository, OwnerService ownerService, EventRepository eventRepository) {
        this.dogRepository = dogRepository;
        this.ownerService = ownerService;
        this.eventRepository = eventRepository;
    }


    public List<DogProfileDto> showDogs() {
        List<Dog> dogList = dogRepository.findAll();
        List<DogProfileDto> dogProfileDtoList =
                dogList.stream()
                .map(dog -> DogProfileDto.fromEntity(dog))
                .collect(Collectors.toList());
        return dogProfileDtoList;
    }

    public DogProfileDto showDogProfile(@PathVariable Long id) throws NoSuchElementException {
        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Wrong id.")
        );
        return DogProfileDto.fromEntity(dog);
    }

    @Transactional
    public DogProfileDto createDog(DogProfileDto dogProfileDto) throws IllegalArgumentException {
        if (dogProfileDto.getId() != null) {
            throw new IllegalArgumentException("id can't be given.");
        }
        Dog newDog = dogProfileDto.toEntity(ownerService);
        dogRepository.save(newDog);
        return DogProfileDto.fromEntity(newDog);
    }

    @Transactional
    public DogUpdateDto updateDog(@PathVariable Long id, DogUpdateDto dogUpdateDto) {
        Dog newDog = dogUpdateDto.toEntity();
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null || !id.equals(target.getId())) {
            return null;
        }
        if (!target.patch(newDog)) {
            return null;
        }
        dogRepository.save(target);
        return DogUpdateDto.fromEntity(target);
    }

    @Transactional
    public DogProfileDto deleteDog(@PathVariable Long id) {
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        dogRepository.delete(target);
        return DogProfileDto.fromEntity(target);
    }

    public DogProfileDto joinEvent(@RequestBody DogEventUpdateDto dogEventUpdateDto) {
        Event targetEvent = eventRepository.findById(dogEventUpdateDto.getDogId()).orElse(null);
        // Validation check for the targetEvent.
        if (targetEvent == null) {
            return null;
        }
        Dog targetDog = dogRepository.findById(dogEventUpdateDto.getParticipatingEventId()).orElse(null);
        // Validation check for the targetDog.
        if (targetDog == null) {
            return null;
        }
        if (!targetDog.addEvent(targetEvent)) {
            return null;
        }
        Dog updated = dogRepository.save(targetDog);
        return DogProfileDto.fromEntity(updated);
    }

    public List<Dog> showFriends(@PathVariable Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElse(null);
        return dog.getFriends();
    }

    public DogFriendsNameDto makeFriends(@PathVariable Long dogId, @RequestBody Long friendId) {
        Dog dog = dogRepository.findById(dogId).orElse(null);
        Dog friend = dogRepository.findById(friendId).orElse(null);
        if (dog == null || friend == null) {
            return null;
        }
        if (dog.getFriends().contains(friend)) {
            return null;
        }
        dog.getFriends().add(friend);
        dogRepository.save(dog);
        friend.getFriends().add(dog);
        dogRepository.save(friend);
        return DogFriendsNameDto.fromEntity(friend);
    }

    public DogFriendsNameDto cancelFriends(@PathVariable Long dogId, @RequestBody Long friendId) {
        Dog dog = dogRepository.findById(dogId).orElse(null);
        Dog friend = dogRepository.findById(friendId).orElse(null);
        if (dog == null || friend == null) {
            return null;
        }
        if (!dog.getFriends().remove(friend)) {
            return null;
        }
        dogRepository.save(dog);
        return DogFriendsNameDto.fromEntity(dogRepository.save(friend));
    }
}
