package com.example.demo.service;

import com.example.demo.dto.EventCreateDto;
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


}