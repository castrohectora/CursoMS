package com.hectorc.bikeservice.controller;

import com.hectorc.bikeservice.dto.BikeDto;
import com.hectorc.bikeservice.entity.Bike;
import com.hectorc.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class CarController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();

        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id) {
        Bike bike = bikeService.getBikeById(id);

        if (bike == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody BikeDto bikeDto) {
        Bike nuevo = bikeService.save(bikeDto);

        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.byUserId(userId);

        return ResponseEntity.ok(bikes);
    }

}
