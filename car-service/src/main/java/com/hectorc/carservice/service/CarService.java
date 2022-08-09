package com.hectorc.carservice.service;

import com.hectorc.carservice.dto.CarDto;
import com.hectorc.carservice.entity.Car;
import java.util.List;

public interface CarService {

    public List<Car> getAll();

    public Car getCarById(int id);

    public Car save(CarDto userDto);

    public List<Car> byUserId(int userId);

}
