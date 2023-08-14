package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.entity.Dog;
import com.example.demo.repository.DogRepository;
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

    public Dog createDog(DogDto dogDto) {
        // 홍팍쌤은 entity레벨에서 id를 조회했는데, 내 생각에는 미리 조회하는 게 더 좋을 것 같다.
        // 차이가 있는지?
        if (dogDto.getId() != null) {
            return null;
        }
        Dog newDog = dogDto.toEntity();
        return dogRepository.save(newDog);
    }

    public Dog updateDog(@PathVariable Long id, DogDto dogDto) {
        Dog newDog = dogDto.toEntity();
        Dog target = dogRepository.findById(id).orElse(null);
        if (target == null || !id.equals(target.getId())) {
            return null;
        }
        if (!target.patch(newDog)) {
            return null;
        }
        return dogRepository.save(target);
    }
}
