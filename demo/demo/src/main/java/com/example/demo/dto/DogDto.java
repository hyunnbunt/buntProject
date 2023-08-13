package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import com.example.demo.entity.Owner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class DogDto {
    Long id;
    Owner owner;
    String name;
    Integer age;
    Long weight;
    String sex;
    Set<Dog> friends;
    Integer happinessPoints;
    Set<Event> organizingEvents;
    Set<Event> participatingEvents;
    public Dog toEntity() {
        return new Dog(id, owner, name, age, weight, sex, friends, happinessPoints, organizingEvents, participatingEvents);
    }
}
