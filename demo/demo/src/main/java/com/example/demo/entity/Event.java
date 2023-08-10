//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//
//import java.util.Set;
//
//@Entity
//public class Event {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//
//    Long date;
//
//    Long time;
//
//    Long latitude;
//
//    Long longitude;
//
//    @ManyToOne
//    Dog organizerDog;
//
//    @ManyToMany
//    Set<Dog> participantDogs;
//}
