package com.example.demo.service;

import com.example.demo.dto.DogEventUpdateDto;
import com.example.demo.dto.DogFriendsNameDto;
import com.example.demo.dto.OwnersDogProfileDto;
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

    public List<OwnersDogProfileDto> showDogs() {
        List<Dog> dogList = dogRepository.findAll();
        return dogList.stream()
                .map(OwnersDogProfileDto::fromEntity)
                .collect(Collectors.toList());
    }

    public OwnersDogProfileDto showDogProfile(@PathVariable Long id) throws NoSuchElementException {
        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find the dog.")
        );
        return OwnersDogProfileDto.fromEntity(dog);
    }

    @Transactional
    public OwnersDogProfileDto createDog(OwnersDogProfileDto dogProfileDto) throws IllegalArgumentException {
        if (dogProfileDto.getId() != null) {
            throw new IllegalArgumentException("Id should be null.");
        }
        Dog newDog = dogProfileDto.toEntity(ownerService);
        dogRepository.save(newDog);
        return OwnersDogProfileDto.fromEntity(newDog);
    }

    @Transactional
    public DogUpdateDto updateDog(@PathVariable Long id, @RequestBody DogUpdateDto dogUpdateDto) throws IllegalArgumentException {
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null || !id.equals(target.getId())) {
            throw new IllegalArgumentException("Can't find the dog.");
        }
        Dog newDog = dogUpdateDto.toEntity();
        // 'patch' throws and exception.
        target.patch(newDog);
        dogRepository.save(target);
        return DogUpdateDto.fromEntity(target);
    }

    @Transactional
    public OwnersDogProfileDto deleteDog(@PathVariable Long id) throws NoSuchElementException {
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null) {
            throw new NoSuchElementException("Can't find the dog.");
        }
        dogRepository.delete(target);
        return OwnersDogProfileDto.fromEntity(target);
    }

    @Transactional
    public OwnersDogProfileDto joinEvent(@RequestBody DogEventUpdateDto dogEventUpdateDto) throws NoSuchElementException, IllegalArgumentException {
        Dog targetDog = dogRepository.findById(dogEventUpdateDto.getDogId()).orElseThrow(
                () -> new NoSuchElementException("Can't find the dog."));
        Event targetEvent = eventRepository.findById(dogEventUpdateDto.getParticipatingEventId()).orElseThrow(
                () -> new NoSuchElementException("Can't find the event."));
        if (!targetDog.addEvent(targetEvent)) {
            throw new IllegalArgumentException("The dog is already participating the event.");
        }
        Dog updated = dogRepository.save(targetDog);
        return OwnersDogProfileDto.fromEntity(updated);
    }

    @Transactional
    public List<DogFriendsNameDto> showFriends(@PathVariable Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(
                () -> new NoSuchElementException("Can't find the dog.")
        );
        return dog.getFriends().stream()
                        .map(DogFriendsNameDto::fromEntity)
                        .collect(Collectors.toList());
    }

    @Transactional
    public DogFriendsNameDto makeFriends(@PathVariable Long dogId, @RequestBody Long friendId) throws NoSuchElementException, IllegalArgumentException {
        Dog dog = dogRepository.findById(dogId).orElse(null);
        Dog friend = dogRepository.findById(friendId).orElse(null);
        if (dog == null || friend == null) {
            throw new NoSuchElementException("Can't find thd dog.");
        }
        if (dog.getFriends().contains(friend)) {
            throw new IllegalArgumentException("The dogs are already friends.");
        }
        dog.getFriends().add(friend);
        dogRepository.save(dog);
        friend.getFriends().add(dog);
        dogRepository.save(friend);
        return DogFriendsNameDto.fromEntity(friend);
    }

    @Transactional
    public DogFriendsNameDto cancelFriends(@PathVariable Long dogId, @RequestBody Long friendId) throws NoSuchElementException, IllegalArgumentException {
        Dog dog = dogRepository.findById(dogId).orElse(null);
        Dog friend = dogRepository.findById(friendId).orElse(null);
        if (dog == null || friend == null) {
            throw new NoSuchElementException("Can't find thd dog.");
        }
        if (!dog.getFriends().remove(friend) || !(friend.getFriends().remove(dog))) {
            throw new IllegalArgumentException("The dogs are already not friends.");
        }
        dogRepository.save(dog);
        return DogFriendsNameDto.fromEntity(dogRepository.save(friend));
    }
}
