package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Slf4j
public class demoController {
    @GetMapping("/dogs")
    public String showDogs() {
        return "dogs/showDogs";
    }
}
