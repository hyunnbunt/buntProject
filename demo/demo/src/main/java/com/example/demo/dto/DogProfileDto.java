package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import com.example.demo.entity.Location;
import com.example.demo.entity.Owner;
import com.example.demo.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DogProfileDto {
    Long id;
    Long dogsOwnerId;
    String name;
    Double age;
    Double weight;
    String sex;
    Set<Event> participatingEvents;
    Set<Location> walkingLocations;
    Set<String> dogFriendNames;

    public DogProfileDto(Long dogsOwnerId, String name, Double age, Double weight, String sex) {
        this.dogsOwnerId = dogsOwnerId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "DogProfileDto{" +
                "dogsOwnerId=" + this.dogsOwnerId +
                ", name='" + this.name + '\'' +
                ", age=" + this.age +
                ", weight=" + this.weight +
                ", sex='" + this.sex + '\'';
    }

    public Dog toEntity(OwnerService ownerService) throws IllegalArgumentException {
        Dog dogEntity = new Dog();
        if (this.dogsOwnerId == null) {
            throw new IllegalArgumentException("Owner id is required.");
        }
        Owner dogsOwner = ownerService.getOwnerEntity(this.dogsOwnerId);
        if (dogsOwner == null) {
            throw new IllegalArgumentException("Can't find the owner.");
        }
        dogEntity.setId(this.id);
        dogEntity.setOwner(dogsOwner);
        dogEntity.setName(this.name);
        dogEntity.setAge(this.age);
        dogEntity.setWeight(this.weight);
        dogEntity.setSex(this.sex);
        if (this.getParticipatingEvents() != null || this.getWalkingLocations() != null || this.getDogFriendNames() != null) {
            throw new IllegalArgumentException("Some information are not allowed to be given.");
        }
        // always null when this method is executed :
        // can't join an event while creating new dog profile.
        return dogEntity;
    }

    public static DogProfileDto fromEntity(Dog dog) {
        DogProfileDto dogProfileDto = new DogProfileDto();
        dogProfileDto.setId(dog.getId());
        dogProfileDto.setDogsOwnerId(dog.getOwner().getId());
        dogProfileDto.setName(dog.getName());
        dogProfileDto.setAge(dog.getAge());
        dogProfileDto.setWeight(dog.getWeight());
        dogProfileDto.setSex(dog.getSex());
        dogProfileDto.setParticipatingEvents(dog.getParticipatingEvents());
        dogProfileDto.setWalkingLocations(dog.getWalkLocations());
        Set<String> friendsNames = dog.getFriends().stream().map(Dog::getName).collect(Collectors.toSet());
        dogProfileDto.setDogFriendNames(friendsNames);
        return dogProfileDto;
    }
}
