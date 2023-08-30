package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import com.example.demo.entity.Location;
import com.example.demo.entity.Owner;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
@Slf4j
public class DogService {
    private final DogRepository dogRepository;
    private final OwnerService ownerService;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;


    @Autowired
    public DogService(DogRepository dogRepository, OwnerService ownerService, EventRepository eventRepository, LocationRepository locationRepository) {
        this.dogRepository = dogRepository;
        this.ownerService = ownerService;
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    public List<DogDto> showDogs() {
        List<Dog> dogList = dogRepository.findAll();
        return dogList.stream().map(DogDto::fromEntity).toList();
    }

    public DogDto showDogProfile(@PathVariable Long id) throws NoSuchElementException {
        Dog dog = dogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return DogDto.fromEntity(dog);
    }

    @Transactional
    public DogDto createDog(@RequestBody DogCreateDto dogCreateDto) {
        Dog dog = this.toEntity(dogCreateDto);
        Dog created = dogRepository.save(dog);
        return DogDto.fromEntity(created);
    }

    /** Convert a DogCreateDto instance to a Dog instance. Used in createDog method. */
    private Dog toEntity(DogCreateDto dogCreateDto) {
        Owner owner = ownerService.getOwner(dogCreateDto.getOwnerId());
        Dog dog = new Dog();
        dog.setOwner(owner);
        dog.setName(dogCreateDto.getName());
        dog.setAge(dogCreateDto.getAge());
        dog.setWeight(dogCreateDto.getWeight());
        dog.setSex(dogCreateDto.getSex());
        return dog;
    }

    @Transactional
    public DogDto updateDog(@PathVariable Long id, @RequestBody DogUpdateDto dogUpdateDto) throws EntityNotFoundException {
        Dog target = dogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.patch(target, dogUpdateDto);
        Dog updated = dogRepository.save(target);
        return DogDto.fromEntity(updated);
    }

    /** Patch a Dog instance with a DogUpdateDto instance's data. Used in updateDog method. */
    public void patch(Dog target, DogUpdateDto dogUpdateDto) {
        if (dogUpdateDto.getName() != null) {
            target.setName(dogUpdateDto.getName());
        }
        if (dogUpdateDto.getAge() != null) {
            target.setAge(dogUpdateDto.getAge());
        }
        if (dogUpdateDto.getWeight() != null) {
            target.setWeight(dogUpdateDto.getWeight());
        }
        if (dogUpdateDto.getSex() != null) {
            target.setSex(dogUpdateDto.getSex());
        }
    }


    @Transactional
    public DogDto deleteDog(@PathVariable Long id) throws EntityNotFoundException {
        Dog target = dogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.emptyFriendsList();
        dogRepository.delete(target);
        return DogDto.fromEntity(target);
    }

    @Transactional
    public DogDto joinEvent(@RequestBody DogEventUpdateDto dogEventUpdateDto) throws EntityNotFoundException, IllegalArgumentException {
        Dog dog = dogRepository.findById(dogEventUpdateDto.getDogId()).orElseThrow(EntityNotFoundException::new);
        Event event = eventRepository.findById(dogEventUpdateDto.getTargetEventId()).orElseThrow(EntityNotFoundException::new);
        if (!dog.addEvent(event)) {
            throw new IllegalArgumentException("The dog is already participating the event.");
        }
        Dog eventAdded = dogRepository.save(dog);
        return DogDto.fromEntity(eventAdded);
    }

    @Transactional
    public DogDto cancelEvent(DogEventUpdateDto dogEventUpdateDto) throws EntityNotFoundException, IllegalArgumentException{
        Dog dog = dogRepository.findById(dogEventUpdateDto.getDogId()).orElseThrow(EntityNotFoundException::new);
        Event event = eventRepository.findById(dogEventUpdateDto.getTargetEventId()).orElseThrow(EntityNotFoundException::new);
        if (!dog.removeEvent(event)) {
            throw new IllegalArgumentException("The dog isn't participating the event.");
        }
        Dog eventRemoved = dogRepository.save(dog);
        return DogDto.fromEntity(eventRemoved);

    }

    @Transactional
    public List<DogDto> showFriends(@PathVariable Long dogId) throws EntityNotFoundException {
        Dog dog = dogRepository.findById(dogId).orElseThrow(EntityNotFoundException::new);
        return dog.getFriends().stream().map(DogDto::fromEntity).toList();
    }

    @Transactional
    public DogDto makeFriends(@PathVariable Long dogId, @RequestBody DogFriendDto dogFriendDto) throws EntityNotFoundException, IllegalArgumentException {
        Dog dog = dogRepository.findById(dogId).orElseThrow(EntityNotFoundException::new);
        Dog friend = dogRepository.findById(dogFriendDto.getFriendId()).orElseThrow(EntityNotFoundException::new);
        if (!this.makeFriends(dog, friend)) {
            throw new IllegalArgumentException("The dogs are already friends.");
        }
        dogRepository.save(dog);
        Dog newFriend = dogRepository.save(friend);
        return DogDto.fromEntity(newFriend);
    }

    /** Take two Dog instances and add one to the other's friend Set. Used in makeFriends method. */
    public boolean makeFriends(Dog dog, Dog friend) {
        return dog.getFriends().add(friend);
    }

    @Transactional
    public DogDto cancelFriends(@PathVariable Long dogId, @RequestBody DogFriendDto dogFriendDto) throws EntityNotFoundException, IllegalArgumentException {
        Dog dog = dogRepository.findById(dogId).orElseThrow(EntityNotFoundException::new);
        Dog friend = dogRepository.findById(dogFriendDto.getFriendId()).orElseThrow(EntityNotFoundException::new);
        if (!cancelFriends(dog, friend)) {
            throw new IllegalArgumentException("The dogs are already not friends.");
        }
        dogRepository.save(dog);
        Dog exFriend = dogRepository.save(friend);
        return DogDto.fromEntity(exFriend);
    }


    /** Take two Dog instances and remove one from the other's friend Set. Used in cancelFriends method. */
    public boolean cancelFriends(Dog dog, Dog friend) {
        return dog.getFriends().remove(friend) && friend.getFriends().remove(dog);
    }


    @Transactional
    public LocationDto joinLocation(DogLocationUpdateDto dogLocationUpdateDto) throws EntityNotFoundException, IllegalArgumentException {
        Dog dog = dogRepository.findById(dogLocationUpdateDto.getDogId()).orElseThrow(EntityNotFoundException::new);
        Location location = locationRepository.findById(dogLocationUpdateDto.getWalkingLocation()).orElseThrow(EntityNotFoundException::new);
        if (!dog.joinLocation(location)) {
            throw new IllegalArgumentException("The dog is already participating the event.");
        }
        dogRepository.save(dog);
        Location joined = locationRepository.save(location);
        return LocationDto.fromEntity(joined);
    }

}
