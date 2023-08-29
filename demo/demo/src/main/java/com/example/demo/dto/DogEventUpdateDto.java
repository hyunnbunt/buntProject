package com.example.demo.dto;

import com.example.demo.entity.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.intellij.lang.annotations.JdkConstants;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.NumberFormat;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DogEventUpdateDto {
    @NotNull
    Long dogId;
    @NotNull
    Long targetEventId;
}
