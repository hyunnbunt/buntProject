package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    Owner owner;
    @Column
    String name;
    @Column
    Integer age;
    @Column
    Long weight;
    @Column
    String sex;
    @ManyToMany
    Set<Dog> friends;
    @Column
    Integer happinessPoints;
    @OneToMany(mappedBy = "organizerDog", cascade = CascadeType.REMOVE)
    Set<Event> organizingEvents;
    @ManyToMany
    Set<Event> participatingEvents;

}
