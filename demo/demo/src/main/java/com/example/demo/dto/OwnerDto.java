package com.example.demo.dto;

import com.example.demo.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class OwnerDto {
    Long id;
    String name;
    Set<DogProfileDto> dogs;
    Long ownerPoints;
}
