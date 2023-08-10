package com.example.demo.repository;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
