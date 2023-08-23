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
        return dogList.stream()
                .map(DogProfileDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DogProfileDto showDogProfile(@PathVariable Long id) throws NoSuchElementException {
        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find the dog.")
        );
        return DogProfileDto.fromEntity(dog);
    }

    @Transactional
    public DogProfileDto createDog(DogProfileDto dogProfileDto) throws IllegalArgumentException {
        if (dogProfileDto.getId() != null) {
            throw new IllegalArgumentException("Id should be null.");
        }
        Dog newDog = dogProfileDto.toEntity(ownerService);
        dogRepository.save(newDog);
        return DogProfileDto.fromEntity(newDog);
    }

    @Transactional
    public DogUpdateDto updateDog(@PathVariable Long id, @RequestBody DogUpdateDto dogUpdateDto) throws IllegalArgumentException {
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null) {
            throw new IllegalArgumentException("Can't find the dog. Wrong id.");
        }
        if (dogUpdateDto.getId() != null && !dogUpdateDto.getId().equals(target.getId())) {
            throw new IllegalArgumentException("Can't update the dog's id.");
        }
        Dog newDog = dogUpdateDto.toEntity();
        // 'patch' throws and exception.
        target.patch(newDog);
        dogRepository.save(target);
        return DogUpdateDto.fromEntity(target);
    }

    @Transactional
    public DogProfileDto deleteDog(@PathVariable Long id) throws NoSuchElementException {
        Dog target = dogRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find the dog.")
        );
        // target.emptyFriendsList();
        dogRepository.delete(target);
        return DogProfileDto.fromEntity(target);
    }

    @Transactional
    public DogProfileDto joinEvent(@RequestBody DogEventUpdateDto dogEventUpdateDto) throws NoSuchElementException, IllegalArgumentException {
        Dog targetDog = dogRepository.findById(dogEventUpdateDto.getDogId()).orElseThrow(
                () -> new NoSuchElementException("Can't find the dog."));
        Event targetEvent = eventRepository.findById(dogEventUpdateDto.getParticipatingEventId()).orElseThrow(
                () -> new NoSuchElementException("Can't find the event."));
        if (!targetDog.addEvent(targetEvent)) {
            throw new IllegalArgumentException("The dog is already participating the event.");
        }
        Dog updated = dogRepository.save(targetDog);
        return DogProfileDto.fromEntity(updated);
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
        Dog dog = dogRepository.findById(dogId).orElseThrow(
                () -> new NoSuchElementException("Can't find thd dog.")
        );
        Dog friend = dogRepository.findById(friendId).orElseThrow(
                () -> new NoSuchElementException("Can't find thd friend dog.")
        );
        // 양쪽을 다 확인하는 것이 좋은가? 이미 엔티티 관계 정의에 의해서 양쪽에 상호 참조하도록 되어 있기는 한데...
        // 나중에 양쪽 중 한 쪽만 팔로잉 하는 식으로 단방향 친구 관계를 맺도록 관계 구성이 바뀐다면 더 수월하도록
        // 양쪽에서 모두 친구 관계를 validate 하는 것이 좋을 것 같다는 생각?
        if (!dog.getFriends().contains(friend) || !friend.getFriends().contains(dog)) {
            throw new IllegalArgumentException("The dogs are already not friends.");
        }
        dog.getFriends().remove(friend);
        friend.getFriends().remove(dog);
        dogRepository.save(dog);
        dogRepository.save(friend);
        return DogFriendsNameDto.fromEntity(dogRepository.save(friend));
    }

}
