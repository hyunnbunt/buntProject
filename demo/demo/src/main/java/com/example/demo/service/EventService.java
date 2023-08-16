package com.example.demo.service;

import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


}
