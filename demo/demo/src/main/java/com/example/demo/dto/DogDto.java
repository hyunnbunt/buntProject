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
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DogDto {
    @NotNull
    Long id;
    Long ownerId;
    String name;
    Double age;
    Double weight;
    String sex;
    Set<Long> participatingEventIds;
    Set<Long> walkingLocationIds;
    Set<Long> friendIds;

    public DogDto(Long ownerId, String name, Double age, Double weight, String sex) {
        this.ownerId = ownerId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "DogProfileDto{" +
                "dogsOwnerId=" + this.ownerId +
                ", name='" + this.name + '\'' +
                ", age=" + this.age +
                ", weight=" + this.weight +
                ", sex='" + this.sex + '\'';
    }

    public Dog toEntity(OwnerService ownerService) throws IllegalArgumentException {
        Dog dogEntity = new Dog();
        if (this.ownerId == null) {
            throw new IllegalArgumentException("Owner id is required.");
        }
        Owner dogsOwner = ownerService.getOwner(this.ownerId);
        if (dogsOwner == null) {
            throw new IllegalArgumentException("Can't find the owner.");
        }
        dogEntity.setId(this.id);
        dogEntity.setOwner(dogsOwner);
        dogEntity.setName(this.name);
        dogEntity.setAge(this.age);
        dogEntity.setWeight(this.weight);
        dogEntity.setSex(this.sex);
//        if (this.getParticipatingEvents() != null || this.getWalkingLocations() != null || this.getDogFriendNames() != null) {
//            throw new IllegalArgumentException("Some information are not allowed to be given.");
//        }
        // always null when this method is executed :
        // can't join an event while creating new dog profile.
        return dogEntity;
    }

    public static DogDto fromEntity(Dog dog) {
        DogDto dogDto = new DogDto();
        dogDto.setId(dog.getId());
        dogDto.setOwnerId(dog.getOwner().getId());
        dogDto.setName(dog.getName());
        dogDto.setAge(dog.getAge());
        dogDto.setWeight(dog.getWeight());
        dogDto.setSex(dog.getSex());
        dogDto.setParticipatingEventIds(dog.getParticipatingEvents().stream().map(Event::getId).collect(Collectors.toSet()));
        dogDto.setWalkingLocationIds(dog.getWalkLocations().stream().map(Location::getId).collect(Collectors.toSet()));
        dogDto.setFriendIds(dog.getFriends().stream().map(Dog::getId).collect(Collectors.toSet()));
        return dogDto;
    }
}
