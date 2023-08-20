package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long latitude;
    @Column(nullable = false)
    Long longitude;
    @ManyToMany
    Set<Dog> walkingDogs;

    public boolean addWalkingDog(Dog dog) {
        if (this.walkingDogs == null) {
            this.walkingDogs = new HashSet();
        }
        if (walkingDogs.contains(dog)) {
            return false;
        }
        walkingDogs.add(dog);
        return true;
    }

}
