package com.example.demo.dto;

import com.example.demo.entity.Dog;
import com.example.demo.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class OwnerDto {
    @NotNull
    Long id;
    String name;
    Set<Dog> dogs;
    Long ownerPoints;

    public static OwnerDto fromEntity(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getDogs(), owner.getOwnerPoints());
    }
}
