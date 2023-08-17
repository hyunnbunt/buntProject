package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public EventDto showEventDetail(@PathVariable Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new IllegalArgumentException("이벤트가 존재하지 않습니다.");
        }
        return EventDto.fromEntity(event);
    }

    public EventDto createEvent(EventDto eventDto) {
        if (eventDto.getId() != null) {
            throw new IllegalArgumentException("잘못된 요청 : 이벤트 id가 지정되지 않아야 합니다.");
        }
        Event createdEvent = eventRepository.save(eventDto.toEntity());
        return EventDto.fromEntity(createdEvent);
    }
}
