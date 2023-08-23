package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Double latitude;
    @Column(nullable = false)
    Double longitude;
    @ManyToMany
    List<Dog> walkingDogs;

    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", walkingDogs=" + walkingDogs +
                '}';
    }

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean addWalkingDog(Dog dog) {
        if (this.walkingDogs == null) {
            this.walkingDogs = new ArrayList<>();
        }
        if (walkingDogs.contains(dog)) {
            return false;
        }
        walkingDogs.add(dog);
        return true;
    }

}
