package com.example.demo.service;

import com.example.demo.dto.OwnerDto;
import com.example.demo.entity.Owner;
import com.example.demo.repository.OwnerRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    public Owner getOwnerEntity(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }
}
