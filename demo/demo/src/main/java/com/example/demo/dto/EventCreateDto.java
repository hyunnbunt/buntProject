package com.example.demo.dto;

import com.example.demo.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EventCreateDto {
    Long id;
    Long date;
    Long time;
    Double latitude;
    Double longitude;
    Long creatorDogId;

    public Event toEntity() {
        Event event = new Event();
        event.setId(this.getId());
        event.setDate(this.getDate());
        event.setTime(this.getTime());
        event.setLatitude(this.getLatitude());
        event.setLongitude(this.getLongitude());
        return event;
    }

    public static EventCreateDto fromEntity(Event event, Long creatorDogId) {
        EventCreateDto eventCreateDto = new EventCreateDto();
        eventCreateDto.setId(event.getId());
        eventCreateDto.setDate(event.getDate());
        eventCreateDto.setTime(event.getTime());
        eventCreateDto.setLatitude(event.getLatitude());
        eventCreateDto.setLongitude(event.getLongitude());
        eventCreateDto.setCreatorDogId(creatorDogId);
        return eventCreateDto;
    }
}
