package com.example.demo.api;

import com.example.demo.dto.EventDto;
import com.example.demo.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
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

    @GetMapping("/events/{id}")
    public ResponseEntity<EventDto> showEventDetail(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(eventService.showEventDetail(id));
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/events")
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        // validation 필요, 들어온 데이터로 dto를 만들 때, null이 될 수 없는 필드가 제대로 들어왔는지 체크하고
        // response 바로 주는 것이 좋을 것 같다. 지금은 왜 null이 아닌지 모르겠음....
        return eventService.createEvent(eventDto);
    }



    // RestAPI
    //    GET (/events) : see all today’s events
    //    POST (/events) : create a new event (body : organizer_Id = Dog.id}
    //    GET (/events/{eventId}) : see the detail of the event
    // PATCH (/dogs/{dogId}) : add an event to participating event column


}
