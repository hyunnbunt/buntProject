package com.example.demo.repository;

import com.example.demo.entity.Event;
import com.example.demo.entity.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
