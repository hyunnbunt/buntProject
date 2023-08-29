package com.example.demo.service;

import com.example.demo.dto.OwnerCreateDto;
import com.example.demo.dto.OwnerDto;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Owner;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final DogRepository dogRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, DogRepository dogRepository) {
        this.ownerRepository = ownerRepository;
        this.dogRepository = dogRepository;
    }

    public OwnerDto showProfile(@PathVariable Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return OwnerDto.fromEntity(owner);
    }

    @Transactional
    public OwnerDto createProfile(OwnerCreateDto ownerCreateDto) throws IllegalArgumentException {
        Owner owner = ownerCreateDto.toEntity();
        Owner created = ownerRepository.save(owner);
        return OwnerDto.fromEntity(created);
    }
    @Transactional
    public OwnerDto deleteProfile(@PathVariable Long ownerId) throws EntityNotFoundException {
        Owner targetOwner = ownerRepository.findById(ownerId).orElseThrow(EntityNotFoundException::new);
        ownerRepository.delete(targetOwner);
        Set<Dog> targetDogs = targetOwner.getDogs();
        for (Dog target : targetDogs) {
            dogRepository.delete(target);
        }
        return OwnerDto.fromEntity(targetOwner);
    }

    /** It will be used by createDog method in DogService. */
    public Owner getOwner(Long id) throws EntityNotFoundException {
        return ownerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
