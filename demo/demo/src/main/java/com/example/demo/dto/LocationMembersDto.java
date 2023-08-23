package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
public class LocationMembersDto {
    Long id;
    Double latitude;
    Double longitude;
    Set<Long> dogIds;

    public static LocationMembersDto fromEntity(Location updatedLocation, Set<Long> dogIds) {
        LocationMembersDto locationMembersDto = new LocationMembersDto();
        locationMembersDto.setId(updatedLocation.getId());
        locationMembersDto.setLatitude(updatedLocation.getLatitude());
        locationMembersDto.setLongitude(updatedLocation.getLongitude());
        locationMembersDto.setDogIds(dogIds);
        return locationMembersDto;
    }

    public static LocationMembersDto fromEntity(Location updatedLocation) {
        LocationMembersDto locationMembersDto = new LocationMembersDto();
        locationMembersDto.setId(updatedLocation.getId());
        locationMembersDto.setLatitude(updatedLocation.getLatitude());
        locationMembersDto.setLongitude(updatedLocation.getLongitude());
        Set<Dog> dogs = updatedLocation.getWalkingDogs();
        Set<Long> dogIds =
                dogs.stream().map(Dog::getId).collect(Collectors.toSet());
        locationMembersDto.setDogIds(dogIds);
        return locationMembersDto;
    }
}
