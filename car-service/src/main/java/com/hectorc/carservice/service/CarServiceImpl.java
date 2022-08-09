package com.hectorc.carservice.service;

import com.hectorc.carservice.dto.CarDto;
import com.hectorc.carservice.entity.Car;
import com.hectorc.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car save(CarDto carDto) {
        Car car = Car.builder().brand(carDto.getBrand()).model(carDto.getModel()).userId(carDto.getUserId()).build();

        Car nuevo = carRepository.save(car);

        return nuevo;
    }

    public List<Car> byUserId(int userId) {
        return carRepository.findByUserId(userId);
    }
}
