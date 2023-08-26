package com.example.demo.api;

import com.example.demo.dto.OwnerDto;
import com.example.demo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("owners/{ownerId}")
    public ResponseEntity<OwnerDto> deleteProfile(@PathVariable Long ownerId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ownerService.deleteProfile(ownerId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
