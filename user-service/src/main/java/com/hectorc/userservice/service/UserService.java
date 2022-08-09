package com.hectorc.userservice.service;

import com.hectorc.userservice.dto.UserDto;
import com.hectorc.userservice.entity.User;
import com.hectorc.userservice.model.Bike;
import com.hectorc.userservice.model.Car;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAll();

    User getUserById(int id);

    User saveCar(UserDto userDto);

    List<Car> getCars(int userId);

    List<Bike> getBikes(int userId);

    Car saveCar(int userId, Car car);

    Bike saveBike(int userId, Bike bike);

    Map<String, Object> getUserAndVehicles(int userId);

}
