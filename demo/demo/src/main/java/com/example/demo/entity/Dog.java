package com.example.demo.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Owner owner;
    String name;
    int age;
    Long weight;
    String sex;
//    @ManyToMany
//    Set<Dog> friends;
//    @OneToMany(mappedBy = "dog")
//    Set<Location> locations;
    int happinessPoints;
    @OneToMany(mappedBy = "organizerDog")
    Set<Event> organizingEvents;
    @ManyToMany
    Set<Event> participatingEvents;

}
