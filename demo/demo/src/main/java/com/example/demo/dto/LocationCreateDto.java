package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class LocationCreateDto {
    @NotNull
    Double latitude;
    @NotNull
    Double longitude;
    @NotNull
    Long creatorDogId;
}
