package com.example.demo.dto;

import com.example.demo.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LocationListProfileDto {
    Long id;
    Double latitude;
    Double longitude;

    public static LocationListProfileDto fromEntity(Location location) {
        LocationListProfileDto locationListProfileDto = new LocationListProfileDto();
        locationListProfileDto.setId(location.getId());
        locationListProfileDto.setLatitude(location.getLatitude());
        locationListProfileDto.setLongitude(location.getLongitude());
        return locationListProfileDto;
    }
}
