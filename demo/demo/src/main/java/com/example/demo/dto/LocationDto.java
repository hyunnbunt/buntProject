package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
public class LocationDto {
    @NotNull
    Long id;
    Double latitude;
    Double longitude;
    Set<Long> walkingDogIds;

    public static LocationDto fromEntity(Location updatedLocation) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(updatedLocation.getId());
        locationDto.setLatitude(updatedLocation.getLatitude());
        locationDto.setLongitude(updatedLocation.getLongitude());
        Set<Dog> walkingDogs = updatedLocation.getWalkingDogs();
        locationDto.setWalkingDogIds(
                walkingDogs.stream().map(Dog::getId).
                        collect(Collectors.toSet())
        );
        return locationDto;
    }

}
