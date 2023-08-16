package com.example.demo.dto;

import com.example.demo.entity.Owner;
import com.example.demo.service.OwnerService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Setter
@Component
public class DogShowMustacheDto {
    Long id;
    String dogsOwnerName;
    String name;
    Integer age;
    Long weight;
    String sex;
    static OwnerService ownerService;

    @Autowired
    public DogShowMustacheDto(OwnerService ownerService) {
        DogShowMustacheDto.ownerService = ownerService;
    }
    public static DogShowMustacheDto fromDogProfileDto(DogProfileDto dogProfileDto) {
        Long dogsOwnerId = dogProfileDto.getDogsOwnerId();
        Owner owner = ownerService.getOwnerEntity(dogsOwnerId);

        DogShowMustacheDto dogShowMustacheDto = new DogShowMustacheDto();
        dogShowMustacheDto.setId(dogProfileDto.getId());
        dogShowMustacheDto.setDogsOwnerName(owner.getName());
        dogShowMustacheDto.setName(dogProfileDto.getName());
        dogShowMustacheDto.setAge(dogProfileDto.getAge());
        dogShowMustacheDto.setWeight(dogProfileDto.getWeight());
        dogShowMustacheDto.setSex(dogProfileDto.getSex());
        return dogShowMustacheDto;
    }

}
