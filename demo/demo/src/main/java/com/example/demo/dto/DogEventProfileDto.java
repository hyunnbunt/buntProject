package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@NoArgsConstructor
@Getter
public class DogEventProfileDto {
    Long dogId;
    String dogName;
    Set<Event> participatingEvents;

    public static DogEventProfileDto fromEntity(Dog dog) {
        DogEventProfileDto dogEventProfileDto = new DogEventProfileDto();
        dogEventProfileDto.setDogId(dog.getId());
        dogEventProfileDto.setDogName(dog.getName());
        dogEventProfileDto.setParticipatingEvents(dog.getParticipatingEvents());
        return dogEventProfileDto;
    }
}
