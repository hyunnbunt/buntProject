package com.example.demo.service;

import com.example.demo.dto.OwnerDto;
import com.example.demo.entity.Owner;
import com.example.demo.repository.OwnerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    public Owner getOwnerEntity(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public OwnerDto showProfile(@PathVariable Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Wrong id.")
        );
        return OwnerDto.fromEntity(owner);
    }
}
