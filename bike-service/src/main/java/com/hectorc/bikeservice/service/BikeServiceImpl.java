package com.hectorc.bikeservice.service;

import com.hectorc.bikeservice.dto.BikeDto;
import com.hectorc.bikeservice.entity.Bike;
import com.hectorc.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(BikeDto bikeDto) {
        Bike car = Bike.builder().brand(bikeDto.getBrand()).model(bikeDto.getModel()).userId(bikeDto.getUserId()).build();

        Bike nuevo = bikeRepository.save(car);

        return nuevo;
    }

    public List<Bike> byUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }
}
