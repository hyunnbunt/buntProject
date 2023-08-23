package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class LocationCreateDto {
    Long id;
    Double latitude;
    Double longitude;
    Long creatorDogId;

    public static LocationCreateDto fromEntity(Location createdLocation, Long creatorDogId) {
        LocationCreateDto locationCreateDto = new LocationCreateDto();
        locationCreateDto.setId(createdLocation.getId());
        locationCreateDto.setLongitude(createdLocation.getLongitude());
        locationCreateDto.setLatitude(createdLocation.getLatitude());
        locationCreateDto.setCreatorDogId(creatorDogId);
        return locationCreateDto;
    }

    /** Convert a dto(LocationCreateDto) to an entity(Location). */
    public Location toEntity() {
        Location location = new Location();
        location.setId(this.getId());
        location.setLatitude(this.getLatitude());
        location.setLongitude(this.getLongitude());
        return location;
    }
}
