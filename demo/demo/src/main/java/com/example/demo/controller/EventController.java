package com.example.demo.controller;

import com.example.demo.dto.EventDto;
import com.example.demo.service.DogService;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {

    private EventService eventService;
    private DogService dogService;

    public EventController(EventService eventService, DogService dogService) {
        this.eventService = eventService;
        this.dogService = dogService;
    }

    @GetMapping("/bunt-project/events")
    public List<EventDto> showEvents(Model model) {
        
    }



    // RestAPI
    //    GET (/events) : see all today’s events
    //    POST (/events) : create a new event (body : organizer_Id = Dog.id}
    //    GET (/events/{eventId}) : see the detail of the event
    // PATCH (/dogs/{dogId}) : add an event to participating event column

}
