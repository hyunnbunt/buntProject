package com.example.demo.service;

import com.example.demo.dto.OwnerDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Owner;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.OwnerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@NoArgsConstructor
public class OwnerService {

    OwnerRepository ownerRepository;
    DogRepository dogRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, DogRepository dogRepository) {
        this.ownerRepository = ownerRepository;
        this.dogRepository = dogRepository;
    }

    public Owner getOwnerEntity(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public OwnerDto showProfile(@PathVariable Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find the owner. Wrong id.")
        );
        return OwnerDto.fromEntity(owner);
    }

    public OwnerDto deleteProfile(@PathVariable Long ownerId) {
        Owner targetOwner = ownerRepository.findById(ownerId).orElseThrow(
                () -> new NoSuchElementException("Can't find the owner. Wrong id.")
        );
        ownerRepository.delete(targetOwner);
        Set<Dog> targetDogs = targetOwner.getDogs();
        for (Dog target : targetDogs) {
            dogRepository.delete(target);
        }
        return OwnerDto.fromEntity(targetOwner);
    }
}
