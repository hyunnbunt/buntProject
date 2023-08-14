package com.example.demo.api;

import com.example.demo.dto.DogDto;
import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
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
    public ResponseEntity<DogDto> createDog(@RequestBody DogDto dogDto) {
        // dto를 entity로 변환하는 것도 service에 맡김
       DogDto createdDogDto = dogService.createDog(dogDto);
       // 이미 dogService에서 성공적으로 db에 저장해서 반환한 entity가 null이 될 수 있나?
        // 될 수 있다. db에 저장 요청을 받아서 성공적으로 리턴은 받았어도, db에서 어떠한 에러가 있었으면
        // repository에서 null을 리턴한다.
        // 그렇다고 해도 왜 HttpStatus 코드가 왜 Bad request인지?
       return (createdDogDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createdDogDto):
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("dogs/{id}")
    public ResponseEntity<DogDto> updateDog(@PathVariable Long id, @RequestBody DogDto dogDto) {
        DogDto updatedDogDto = dogService.updateDog(id, dogDto);
        return (updatedDogDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(updatedDogDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("dogs/{id}")
    public ResponseEntity<DogDto> deleteDog(@PathVariable Long id) {
        DogDto deletedDogDto = dogService.deleteDog(id);
        return (deletedDogDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(deletedDogDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
