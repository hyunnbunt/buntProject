package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long latitude;
    @Column(nullable = false)
    Long longitude;
    @ManyToMany(mappedBy = "walkLocations")
    Set<Dog> dogs;
}
