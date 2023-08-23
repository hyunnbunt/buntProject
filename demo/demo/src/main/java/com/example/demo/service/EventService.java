package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;

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

    public EventDto showEventDetail(@PathVariable Long id) throws NoSuchElementException {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Wrong id.")
        );
        return EventDto.fromEntity(event);
    }

    public EventDto createEvent(EventDto eventDto) throws IllegalArgumentException {
        if (eventDto.getId() != null) {
            throw new IllegalArgumentException("Id should be null.");
        }
        Event createdEvent = eventRepository.save(eventDto.toEntity());
        return EventDto.fromEntity(createdEvent);
    }
}
