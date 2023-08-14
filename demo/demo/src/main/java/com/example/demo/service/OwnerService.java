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
        return ownerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다. 보호자가 존재하지 않는 강아지.")
        );
    }
}
