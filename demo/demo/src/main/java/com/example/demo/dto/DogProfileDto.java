package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Owner;
import com.example.demo.service.OwnerService;
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

    public Dog toEntity(OwnerService ownerService) {
        Dog dogEntity = new Dog();
        // toEntity를 부른 메소드에서
        if (dogsOwnerId == null) {
            throw new IllegalArgumentException("보호자 id가 누락되었습니다.");
        }
        Owner dogsOwner = ownerService.getOwnerEntity(dogsOwnerId);
        // dogsOwner shouldn't be null.
        if (dogsOwner == null) {
            throw new IllegalArgumentException("보호자 id가 잘못되었습니다.");
        }
        dogEntity.setId(this.id);
        dogEntity.setOwner(dogsOwner);
        dogEntity.setName(this.name);
        dogEntity.setAge(this.age);
        dogEntity.setWeight(this.weight);
        dogEntity.setSex(this.sex);
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
        return dogProfileDto;
    }
}
