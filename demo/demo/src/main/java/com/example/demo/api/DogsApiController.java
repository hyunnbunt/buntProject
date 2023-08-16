package com.example.demo.api;

import com.example.demo.dto.DogProfileDto;
import com.example.demo.dto.DogUpdateDto;
import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DogsApiController {
    private DogService dogService;
    @Autowired
    public DogsApiController(DogService dogService) {
        this.dogService = dogService;
    }
    @GetMapping("dogs")
    public List<DogProfileDto> showDogs() {
        return dogService.showDogs();
    }

    @GetMapping("dogs/{id}")
    public DogProfileDto showDogProfile(@PathVariable Long id) {
        return dogService.showDogProfile(id);
    }

    @PostMapping("dogs")
    public ResponseEntity<DogProfileDto> createDog(@RequestBody DogProfileDto dogProfileDto) {
        // dto를 entity로 변환하는 것도 service에 맡김
       DogProfileDto createdDogProfileDto = dogService.createDog(dogProfileDto);
       // 이미 dogService에서 성공적으로 db에 저장해서 반환한 entity가 null이 될 수 있나?
        // 될 수 있다. db에 저장 요청을 받아서 성공적으로 리턴은 받았어도, db에서 어떠한 에러가 있었으면
        // repository에서 null을 리턴한다.
        // 그렇다고 해도 왜 HttpStatus 코드가 왜 Bad request인지?
       return (createdDogProfileDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createdDogProfileDto):
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("dogs/{id}")
    public ResponseEntity<DogUpdateDto> updateDog(@PathVariable Long id, @RequestBody DogUpdateDto dogUpdateDto) {
        DogUpdateDto updatedDogDto = dogService.updateDog(id, dogUpdateDto);
        return (updatedDogDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(updatedDogDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("dogs/{id}")
    public ResponseEntity<DogProfileDto> deleteDog(@PathVariable Long id) {
        DogProfileDto deletedDogProfileDto = dogService.deleteDog(id);
        return (deletedDogProfileDto != null) ?
            ResponseEntity.status(HttpStatus.OK).body(deletedDogProfileDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
