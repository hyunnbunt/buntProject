package com.example.demo.dto;

import com.example.demo.entity.Event;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@Setter
@Getter
@Slf4j
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
    public Event toEntity() {
//        Event event = new Event();
//        event.setId(this.getId());
//        event.setDate(this.getDate());
//        event.setTime(this.getTime());
//        event.setLatitude(this.getLatitude());
//        event.setLongitude(this.getLongitude());
        Event event = new Event(this.getId(), this.getDate(), this.getTime(), this.getLatitude(), this.getLongitude());
        return event;
    }
}
