package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OwnersDogProfildDto {

    Long id;
    String name;
    Double age;
    Double weight;
    String sex;
    List<Event> participatingEvents;

    @Override
    public String toString() {
        return "DogProfileDto{" +
                "name='" + this.name + '\'' +
                ", age=" + this.age +
                ", weight=" + this.weight +
                ", sex='" + this.sex + '\'';
    }

    public static OwnersDogProfileDto fromEntity(Dog dog) {
        OwnersDogProfileDto ownersDogProfileDto = new OwnersDogProfileDto();
        ownersDogProfileDto.setId(dog.getId());
        ownersDogProfileDto.setName(dog.getName());
        ownersDogProfileDto.setAge(dog.getAge());
        ownersDogProfileDto.setWeight(dog.getWeight());
        ownersDogProfileDto.setSex(dog.getSex());
        ownersDogProfileDto.setParticipatingEvents(dog.getParticipatingEvents());
        return ownersDogProfileDto;
    }
}

