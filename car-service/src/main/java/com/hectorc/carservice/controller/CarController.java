package com.hectorc.carservice.controller;

import com.hectorc.carservice.dto.CarDto;
import com.hectorc.carservice.entity.Car;
import com.hectorc.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();

        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);

        if (car == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody CarDto carDto) {
        Car nuevo = carService.save(carDto);

        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> cars = carService.byUserId(userId);

        return ResponseEntity.ok(cars);
    }

}
