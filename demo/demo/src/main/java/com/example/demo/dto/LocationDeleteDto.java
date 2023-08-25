package com.example.demo.dto;

import com.example.demo.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class LocationDeleteDto {
    Long id;
    Double latitude;
    Double longitude;

    public static LocationDeleteDto fromEntity(Location location) {
        LocationDeleteDto locationDeleteDto = new LocationDeleteDto();
        locationDeleteDto.setId(location.getId());
        locationDeleteDto.setLatitude(location.getLatitude());
        locationDeleteDto.setLongitude(location.getLongitude());
        return locationDeleteDto;
    }
}
