package com.example.demo.dto;

import com.example.demo.entity.Dog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class DogUpdateDto {

    Long id;
    String name;
    Integer age;
    Long weight;
    String sex;

    public Dog toEntity() {
        Dog dogEntity = new Dog();
        dogEntity.setId(this.id);
        dogEntity.setName(this.name);
        dogEntity.setAge(this.age);
        dogEntity.setWeight(this.weight);
        dogEntity.setSex(this.sex);
        return dogEntity;
    }

    public static DogUpdateDto fromEntity(Dog dog) {
        DogUpdateDto dogUpdateDto = new DogUpdateDto();
        dogUpdateDto.setId(dog.getId());
        dogUpdateDto.setName(dog.getName());
        dogUpdateDto.setAge(dog.getAge());
        dogUpdateDto.setWeight(dog.getWeight());
        dogUpdateDto.setSex(dog.getSex());
        return dogUpdateDto;
    }

}
