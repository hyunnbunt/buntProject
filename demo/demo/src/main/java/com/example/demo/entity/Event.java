package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor // Entity는 항상 defaul constructor가 필요
@Getter
@Setter
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

    public Event(Long id, Long date, Long time, Long latitude, Long longitude) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @ManyToOne(optional = true)
    @JoinColumn // (nullable = false)
    Dog organizerDog;

    @ManyToMany(mappedBy = "participatingEvents")
    Set<Dog> participantDogs;
}
