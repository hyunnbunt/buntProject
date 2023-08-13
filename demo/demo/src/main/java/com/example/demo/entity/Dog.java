package com.example.demo.entity;

import jakarta.persistence.*;

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
    @ManyToMany
    Set<Dog> friends;
    int happinessPoints;
    @OneToMany(mappedBy = "organizerDog", cascade = CascadeType.REMOVE)
    Set<Event> organizingEvents;
    @ManyToMany
    Set<Event> participatingEvents;

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", sex='" + sex + '\'' +
                ", friends=" + friends +
                ", happinessPoints=" + happinessPoints +
                ", organizingEvents=" + organizingEvents +
                ", participatingEvents=" + participatingEvents +
                '}';
    }
}
