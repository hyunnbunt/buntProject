package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class DogLocationUpdateDto {
    Long dogId;
    Long joiningLocation;
}
