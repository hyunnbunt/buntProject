package com.example.demo.service;

import com.example.demo.dto.DogUpdateDto;
import com.example.demo.dto.EventCreateDto;
import com.example.demo.dto.EventDto;
import com.example.demo.dto.LocationCreateDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import com.example.demo.entity.Location;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {

    private EventRepository eventRepository;
    private DogRepository dogRepository;

    @Autowired
    public EventService(EventRepository eventRepository, DogRepository dogRepository) {
        this.eventRepository = eventRepository;
        this.dogRepository = dogRepository;
    }

    public List<EventDto> showEvents() {
        List<Event> eventList = eventRepository.findAll();
        return eventList.stream().
                        map(EventDto::fromEntity).toList();
    }

    public EventDto showEventDetail(@PathVariable Long id) throws NoSuchElementException {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Wrong id.")
        );
        return EventDto.fromEntity(event);
    }

    @Transactional
    public EventCreateDto createEvent(@RequestBody EventCreateDto eventCreateDto) throws IllegalArgumentException {
        Event event = eventCreateDto.toEntity();
        if (event.getId() != null) {
            throw new IllegalArgumentException("Id must be null.");
        }
        Long creatorDogId = eventCreateDto.getCreatorDogId();
        if (creatorDogId == null) {
            throw new IllegalArgumentException("You should set your creator dog for the new event.");
        }
        Dog creatorDog = dogRepository.findById(creatorDogId).orElseThrow(
                () -> new IllegalArgumentException("Can't find the dog. Wrong id.")
        );
        if (!event.addParticipatingDog(creatorDog)) {
            throw new IllegalArgumentException("Your dog already added this event.");
        }
        Event createdEvent = eventRepository.save(eventCreateDto.toEntity());
        return EventCreateDto.fromEntity(createdEvent, creatorDogId);
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
