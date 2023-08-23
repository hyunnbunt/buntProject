package com.example.demo.dto;

import com.example.demo.entity.Event;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Slf4j
public class EventDto {

    Long id;
    Long date;
    Long time;
    Double latitude;
    Double longitude;

    public EventDto(Long date, Long time, Double latitude, Double longitude) {
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "date=" + date +
                ", time=" + time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static EventDto fromEntity(Event event) {
        return new EventDto(event.getId(), event.getDate(), event.getTime(), event.getLatitude(), event.getLongitude());
    }
    public Event toEntity() {
        return new Event(this.getId(), this.getDate(), this.getTime(), this.getLatitude(), this.getLongitude());
    }
}
