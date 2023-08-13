package com.example.demo.api;

import com.example.demo.dto.DogDto;
import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class DogsApiController {
    @Autowired
    DogService dogService;
    @GetMapping("dogs")
    public List<Dog> showDogs() {
        return dogService.showDogs();
    }

    @GetMapping("dogs/{id}")
    public Dog showDogProfile(@PathVariable Long id) {
        return dogService.showDogProfile(id);
    }

    @PostMapping("dogs")
    public ResponseEntity<Dog> createDog(@RequestBody DogDto dogDto) {
        // dto를 entity로 변환하는 것도 service에 맡김
       Dog createdDog = dogService.createDog(dogDto);
       // 이미 dogService에서 db에 성공적으로 저장해서 반환한 entity가 null이 될 수 있나?
        // 그렇다고 해도 왜 HttpStatus 코드가 왜 Bad request인지?
       return (createdDog != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createdDog):
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
