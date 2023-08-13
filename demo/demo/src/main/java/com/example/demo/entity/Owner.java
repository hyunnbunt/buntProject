package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
public class Owner {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    Set<Dog> dogs;
    int ownerPoints;
}
