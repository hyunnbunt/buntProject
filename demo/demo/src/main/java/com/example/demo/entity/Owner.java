package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Setter
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


    // 숫자가 커질 것 같으면 Long으로 바꾸기
}
