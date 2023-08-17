package com.example.demo.service;

import com.example.demo.dto.DogEventUpdateDto;
import com.example.demo.dto.DogProfileDto;
import com.example.demo.dto.DogUpdateDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
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

    public DogProfileDto showDogProfile(@PathVariable Long id) {
        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 강아지")
        );
        return DogProfileDto.fromEntity(dog);
    }

    @Transactional
    public DogProfileDto createDog(DogProfileDto dogProfileDto) {
        // 홍팍쌤은 entity레벨에서 id를 조회했는데, 내 생각에는 미리 조회하는 게 더 좋을 것 같다.
        // 차이가 있는지?
        if (dogProfileDto.getId() != null) {
            return null;
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

    public DogEventUpdateDto joinEvent(@PathVariable Long dogId, @PathVariable Long eventId) {
        Event targetEvent = eventRepository.findById(eventId).orElse(null);
        if (targetEvent == null || !eventId.equals(targetEvent.getId())) {
            return null;
        }
        Dog targetDog = dogRepository.findById(dogId).orElse(null);
        if (targetDog == null || !dogId.equals(targetDog.getId())) {
            return null;
        }
        if (!targetDog.addEvent(targetEvent)) {
            return null;
        }
        return new DogEventUpdateDto(targetDog.getId(), targetEvent.getId());
    }
}
