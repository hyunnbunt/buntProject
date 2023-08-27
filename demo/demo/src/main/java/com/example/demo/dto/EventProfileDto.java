package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EventProfileDto {

    Long id;
    Long date;
    Long time;
    Double latitude;
    Double longitude;
    Set<String> participantDogNames;
    public static EventProfileDto fromEntity(Event event) {
        Set<String> participantDogNames =
                event.getParticipantDogs().stream()
                        .map(Dog::getName).collect(Collectors.toSet());
        return new EventProfileDto(event.getId(), event.getDate(), event.getTime(), event.getLatitude(), event.getLongitude(), participantDogNames);
    }
}
