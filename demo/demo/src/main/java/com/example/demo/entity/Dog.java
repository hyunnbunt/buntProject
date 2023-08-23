package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
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
    Double age;
    @Column
    Double weight;
    @Column
    String sex;
    @ManyToMany
    List<Dog> friends;
    @Column
    Integer happinessPoints;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizerDog", cascade = CascadeType.REMOVE)
    List<Event> organizingEvents;
    @ManyToMany
    List<Event> participatingEvents;
    @ManyToMany(mappedBy = "walkingDogs")
    @JsonIgnore
    List<Location> walkLocations;

    public Dog(Owner owner, String name, Double age, Double weight, String sex) {
        this.owner = owner;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
    }

    public void patch(Dog newDog) throws IllegalArgumentException {
        if (newDog.owner == null && newDog.name == null && newDog.age == null && newDog.weight == null && newDog.sex == null) {
            throw new IllegalArgumentException("No update info.");
        }
        if (newDog.owner != null) {
            this.owner = newDog.owner;
        }
        if (newDog.name != null) {
            this.name = newDog.name;
        }
        if (newDog.age != null) {
            this.age = newDog.age;
        }
        if (newDog.weight != null) {
            this.weight = newDog.weight;
        }
        if (newDog.sex != null) {
            this.sex = newDog.sex;
        }
        if (!(newDog.friends == null && newDog.happinessPoints == null && newDog.organizingEvents == null && newDog.participatingEvents == null)) {
            throw new IllegalArgumentException("Not allowed to access.");
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "owner=" + owner.getName() +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean addEvent(Event targetEvent) {
        if (this.participatingEvents == null) {
            this.participatingEvents = new ArrayList<>();
        }
        if (this.participatingEvents.contains(targetEvent)) {
            return false;
        }
        this.participatingEvents.add(targetEvent);
        return true;
    }
}
