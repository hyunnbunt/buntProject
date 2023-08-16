package com.example.demo.dto;

import com.example.demo.entity.Event;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

@Setter
public class EventDto {

    Long id;
    Long date;
    Long time;
    Long latitude;
    Long longitude;


    public static EventDto fromEntity(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setDate(event.getDate());
        eventDto.setTime(event.getTime());
        eventDto.setLatitude(event.getLatitude());
        eventDto.setLongitude(event.getLongitude());
        return eventDto;
    }
}
