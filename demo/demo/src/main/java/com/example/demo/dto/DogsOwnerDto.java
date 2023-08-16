package com.example.demo.dto;

import com.example.demo.entity.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class DogsOwnerDto {
    Long id;
    String name;

    public static DogsOwnerDto fromEntity(Owner owner) {
        DogsOwnerDto dogsOwnerDto = new DogsOwnerDto();
        dogsOwnerDto.setId(owner.getId());
        dogsOwnerDto.setName(owner.getName());
        return dogsOwnerDto;
    }
}
