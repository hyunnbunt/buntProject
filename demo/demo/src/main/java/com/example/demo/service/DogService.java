package com.example.demo.service;

import com.example.demo.dto.DogProfileDto;
import com.example.demo.entity.Dog;
import com.example.demo.repository.DogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DogService {
    @Autowired
    DogRepository dogRepository;

    public List<Dog> showDogs() {
        return dogRepository.findAll();
    }

    public Dog showDogProfile(@PathVariable Long id) {
        return dogRepository.findById(id).orElse(null);
    }

    @Transactional
    public DogProfileDto createDog(DogProfileDto dogProfileDto) {
        // 홍팍쌤은 entity레벨에서 id를 조회했는데, 내 생각에는 미리 조회하는 게 더 좋을 것 같다.
        // 차이가 있는지?
        if (dogProfileDto.getId() != null) {
            return null;
        }
        Dog newDog = dogProfileDto.toEntity();
        dogRepository.save(newDog);
        return DogProfileDto.fromEntity(newDog);
    }

    @Transactional
    public DogProfileDto updateDog(@PathVariable Long id, DogProfileDto dogProfileDto) {
        Dog newDog = dogProfileDto.toEntity();
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null || !id.equals(target.getId())) {
            return null;
        }
        if (!target.patch(newDog)) {
            return null;
        }
        dogRepository.save(target);
        return DogProfileDto.fromEntity(target);
    }

    @Transactional
    public DogProfileDto deleteDog(@PathVariable Long id) {
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        dogRepository.delete(target);
        return DogProfileDto.fromEntity(target);
    }
}
