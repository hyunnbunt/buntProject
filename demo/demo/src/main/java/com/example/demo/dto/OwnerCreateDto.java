package com.example.demo.dto;

import com.example.demo.entity.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class OwnerCreateDto {
    @NotNull
    String name;

    public Owner toEntity() {
        Owner owner = new Owner();
        owner.setName(this.getName());
        return owner;
    }
}
