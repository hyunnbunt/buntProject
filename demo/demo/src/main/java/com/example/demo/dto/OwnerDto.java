package com.example.demo.dto;

import com.example.demo.entity.Owner;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
public class OwnerDto {
    Long id;
    String name;
    Set<DogProfileDto> dogs;
    Long ownerPoints;


}
