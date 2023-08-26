package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Slf4j
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
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Dog> friends;
    @Column
    Integer happinessPoints;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organizerDog", cascade = CascadeType.REMOVE)
    Set<Event> organizingEvents;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Event> participatingEvents;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "walkingDogs")
    Set<Location> walkLocations;

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
            this.participatingEvents = new HashSet<>();
        }
        if (this.participatingEvents.contains(targetEvent)) {
            return false;
        }
        this.participatingEvents.add(targetEvent);
        return true;
    }

    public void emptyFriendsList() {
        Set<Dog> dogFriends = this.getFriends();
        for (Dog friend : dogFriends) {
            friend.getFriends().remove(this);
        }
        this.setFriends(null);
    }

    public boolean cancelEvent(Event targetEvent) {
        // if the first condition is true, it will not check the second condition. No possibility of NullPointerException.
        if (this.participatingEvents == null || !this.participatingEvents.contains(targetEvent)) {
            return false;
        }
        return this.participatingEvents.remove(targetEvent);
    }

}
