package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long date;
    @Column(nullable = false)
    Long time;
    @Column(nullable = false)
    Long latitude;
    @Column(nullable = false)
    Long longitude;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = false)
    Dog organizerDog;

    @ManyToMany(mappedBy = "participatingEvents")
    Set<Dog> participantDogs;
}
