package com.example.demo.dto;

import com.example.demo.controller.DogsController;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Owner;
import com.example.demo.service.DogService;
import com.example.demo.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Setter
@Getter
public class DogProfileDto {
    Long id;
    Long dogsOwnerId;
    String name;
    Integer age;
    Long weight;
    String sex;
    @Autowired
    OwnerService ownerService;
    public Dog toEntity() {
        Dog dogEntity = new Dog();
        dogEntity.setOwner(ownerService.getOwnerEntity(dogsOwnerId));
        dogEntity.setName(this.name);
        dogEntity.setAge(this.age);
        dogEntity.setWeight(this.weight);
        dogEntity.setSex(this.sex);
        return dogEntity;
    }

    public static DogProfileDto fromEntity(Dog dog) {
        DogProfileDto dogProfileDto = new DogProfileDto();
        dogProfileDto.setDogsOwnerId(dog.getOwner().getId());
        dogProfileDto.setName(dog.getName());
        dogProfileDto.setAge(dog.getAge());
        dogProfileDto.setWeight(dog.getWeight());
        dogProfileDto.setSex(dog.getSex());
        return dogProfileDto;
    }
}
