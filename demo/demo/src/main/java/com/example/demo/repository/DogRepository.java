package com.example.demo.repository;

import com.example.demo.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
