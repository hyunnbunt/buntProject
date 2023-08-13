package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long latitude;
    @Column(nullable = false)
    Long longitude;
    @ManyToOne
    @JoinColumn(nullable = false)
    Dog dog;
}
