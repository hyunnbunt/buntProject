package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.entity.Event;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {

    EventService eventService;

    @Autowired
    public EventServiceTest(EventService eventService) {
        this.eventService = eventService;
    }

    @Test
    @Transactional
    void showEvents() {
        EventDto event1 = new EventDto(20230816L, 1530L, 37.541, 126.986);
        EventDto event2 = new EventDto(20231009L, 1900L, 25.66, 166.2);
        EventDto event3 = new EventDto(20231101L, 2050L, 11.11, 55.16);
        List<EventDto> expected = new ArrayList(Arrays.asList(event1, event2, event3));
        List<EventDto> actual = eventService.showEvents();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void showEventDetail() {
        EventDto expected = new EventDto(20230816L, 1530L, 37.541, 126.986);
        EventDto actual = eventService.showEventDetail(1L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void createEvent() {
        EventDto expected = new EventDto(20231225L, 1100L, 56.352, 126.66);
        EventDto actual = eventService.createEvent(expected);
        assertEquals(expected.toString(), actual.toString());
    }
}