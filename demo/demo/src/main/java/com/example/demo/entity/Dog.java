package com.example.demo.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
public class Dog {
    @Column(name="dog_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//    Owner owner;
    String name;
    int age;
    Long weight;
    String sex;
    @ManyToMany
    Set<Dog> friends;
//    @OneToMany(mappedBy = "dog")
//    Set<Location> locations;
    int happinessPoints;
//    @OneToMany
//    Set<Event> participatingEvents;
//    @ManyToMany
//    Set<Event> organizingEvents;
}
