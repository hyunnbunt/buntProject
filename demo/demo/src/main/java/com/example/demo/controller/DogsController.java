package com.example.demo.controller;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogShowMustacheDto;
import com.example.demo.service.DogService;
import com.example.demo.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class DogsController {

    private DogService dogService;
    private OwnerService ownerService;

    @Autowired
    public DogsController(DogService dogService, OwnerService ownerService) {
        this.ownerService = ownerService;
        this.dogService = dogService;
    }

    @GetMapping("bunt-project/dogs")
    public String showDogs(Model model) {
        List<DogDto> dogs = dogService.showDogs();
        List<DogShowMustacheDto> dogShowMustacheDtos =
                dogs.stream().map(dogProfileDto -> DogShowMustacheDto.fromDogProfileDto(dogProfileDto))
                                .toList();

        model.addAttribute("dogs", dogShowMustacheDtos);

        return "dogs/showDogs";
    }
}
