package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDto> showEvents() {
        List<Event> eventList = eventRepository.findAll();
        List<EventDto> eventDtoList =
                eventList.stream().
                        map(event -> EventDto.fromEntity(event)).toList();
        return eventDtoList;
    }
}
