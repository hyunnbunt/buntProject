package com.example.demo.dto;

import com.example.demo.entity.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DogEventUpdateDto {
    Long dogId;
    Long participatingEvent;
}
