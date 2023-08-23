package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
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
    Double latitude;
    @Column(nullable = false)
    Double longitude;

    @ManyToOne(optional = true)
    @JoinColumn // (nullable = false)
    Dog organizerDog;

    @ManyToMany(mappedBy = "participatingEvents")
    @JsonIgnore
    List<Dog> participantDogs;

    public Event(Long id, Long date, Long time, Double latitude, Double longitude) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "Event{" +
                "date='" + this.date + '\'' +
                '}';
    }

}
