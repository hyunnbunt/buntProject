package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    Integer age;
    @Column
    Long weight;
    @Column
    String sex;
    @ManyToMany
    Set<Dog> friends;
    @Column
    Integer happinessPoints;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizerDog", cascade = CascadeType.REMOVE)
    Set<Event> organizingEvents;
    @ManyToMany
    Set<Event> participatingEvents;

    public boolean patch(Dog newDog) {
        if (newDog.owner == null && newDog.name == null && newDog.age == null && newDog.weight == null && newDog.sex == null) {
            return false;
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
        return newDog.friends == null && newDog.happinessPoints == null && newDog.organizingEvents == null && newDog.participatingEvents == null;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean addEvent(Event targetEvent) {
        if (this.participatingEvents == null) {
            this.participatingEvents = new HashSet();
        }
        if (this.participatingEvents.contains(targetEvent)) {
            return false;
        }
        this.participatingEvents.add(targetEvent);
        return true;
    }
}
