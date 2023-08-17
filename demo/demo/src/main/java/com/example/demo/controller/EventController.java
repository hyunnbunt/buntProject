package com.example.demo.controller;

import com.example.demo.dto.EventDto;
import com.example.demo.service.DogService;
import com.example.demo.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class EventController {

    private EventService eventService;
    private DogService dogService;

    @Autowired
    public EventController(EventService eventService, DogService dogService) {
        this.eventService = eventService;
        this.dogService = dogService;
    }

    @GetMapping("bunt-project/events")
    public String showEvents(Model model) {
//        List<EventDto> eventDtoList = eventService.showEvents();
//        if (eventDtoList.isEmpty()) {
//            log.info("No events");
//            return "events/noEvents/";
//        }
//        model.addAttribute("events", eventDtoList);
        return "events/showEvents/";
    }



    // RestAPI
    //    GET (/events) : see all today’s events
    //    POST (/events) : create a new event (body : organizer_Id = Dog.id}
    //    GET (/events/{eventId}) : see the detail of the event
    // PATCH (/dogs/{dogId}) : add an event to participating event column

}
