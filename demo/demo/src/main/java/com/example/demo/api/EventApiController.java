package com.example.demo.api;

import com.example.demo.dto.EventDto;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventApiController {

    private EventService eventService;

    @Autowired
    public EventApiController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventDto> showEvents() {
        return eventService.showEvents();
    }

    // RestAPI
    //    GET (/events) : see all today’s events
    //    POST (/events) : create a new event (body : organizer_Id = Dog.id}
    //    GET (/events/{eventId}) : see the detail of the event
    // PATCH (/dogs/{dogId}) : add an event to participating event column


}
