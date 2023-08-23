package com.example.demo.entity;

import com.example.demo.dto.DogProfileDto;
import com.example.demo.dto.OwnerDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    Set<Dog> dogs;
    @Column
            // @Column 어노테이션이 없으면 이 컬럼 옵션이 nullable = false가 됨
    Long ownerPoints;

    public Owner(String name) {
        this.name = name;
    }

    public static OwnerDto fromEntity(Owner owner) {
        Set<DogProfileDto> dogs =
        owner.getDogs().stream().map(dog -> DogProfileDto.fromEntity(dog)).collect(Collectors.toSet());

        return new OwnerDto(owner.getId(), owner.getName(), dogs, owner.getOwnerPoints());
    }
    // 숫자가 커질 것 같으면 Long으로 바꾸기
}
