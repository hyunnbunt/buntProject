package com.example.demo.dto;

import com.example.demo.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class OwnerDto {
    Long id;
    String name;
    List<OwnersDogProfileDto> dogs;
    Long ownerPoints;

    public static OwnerDto fromEntity(Owner owner) {
        List<OwnersDogProfileDto> dogs =
                owner.getDogs().stream().map(OwnersDogProfileDto::fromEntity).collect(Collectors.toList());
        return new OwnerDto(owner.getId(), owner.getName(), dogs, owner.getOwnerPoints());
    }
}
