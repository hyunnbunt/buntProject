package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NewLocationDto {
    Long id;
    Long latitude;
    Long longitude;
    Long dogId;
}
