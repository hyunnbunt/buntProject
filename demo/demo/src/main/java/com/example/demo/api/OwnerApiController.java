package com.example.demo.api;

import com.example.demo.dto.OwnerCreateDto;
import com.example.demo.dto.OwnerDto;
import com.example.demo.service.OwnerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class OwnerApiController {

    OwnerService ownerService;

    @Autowired
    public OwnerApiController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("owners/{ownerId}")
    public ResponseEntity<OwnerDto> showProfile(@PathVariable Long ownerId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ownerService.showProfile(ownerId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("owners")
    public ResponseEntity<OwnerDto> createProfile(@RequestBody @Validated OwnerCreateDto ownerCreateDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ownerService.createProfile(ownerCreateDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("owners/{ownerId}")
    public ResponseEntity<OwnerDto> deleteProfile(@PathVariable Long ownerId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ownerService.deleteProfile(ownerId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
