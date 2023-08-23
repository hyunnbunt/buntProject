package com.example.demo.service;

import com.example.demo.dto.DogUpdateDto;
import com.example.demo.dto.EventDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Transactional
    public EventDto createEvent(EventDto eventDto) throws IllegalArgumentException {
        if (eventDto.getId() != null) {
            throw new IllegalArgumentException("Id should be null.");
        }
        Event createdEvent = eventRepository.save(eventDto.toEntity());
        return EventDto.fromEntity(createdEvent);
    }

    public EventDto updateEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto) throws IllegalArgumentException {
        Event target = eventRepository.findById(eventId).orElseThrow(
                () -> new IllegalArgumentException("Can't find the event. Wrong id.")
        );
        if (eventDto.getId() != null && !eventDto.getId().equals(target.getId())) {
            throw new IllegalArgumentException("Can't update the event's id.");
        }
        Event event = eventDto.toEntity();
        // 'patch' throws and exception.
        target.patch(event);
        eventRepository.save(target);
        return EventDto.fromEntity(target);
    }

    public EventDto deleteEvent(@PathVariable Long eventId) throws NoSuchElementException, IllegalArgumentException {
        Event target = eventRepository.findById(eventId).orElseThrow(
                () -> new NoSuchElementException("Can't find the event. Wrong id.")
        );
        eventRepository.delete(target);
        return EventDto.fromEntity(target);
    }
}
