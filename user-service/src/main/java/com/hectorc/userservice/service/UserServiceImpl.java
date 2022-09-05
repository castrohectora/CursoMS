package com.hectorc.userservice.service;

import com.hectorc.userservice.dto.UserDto;
import com.hectorc.userservice.entity.User;
import com.hectorc.userservice.feingclients.BikeFeingClient;
import com.hectorc.userservice.feingclients.CarFeingClient;
import com.hectorc.userservice.model.Bike;
import com.hectorc.userservice.model.Car;
import com.hectorc.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTempate;

    @Autowired
    CarFeingClient carFeingClient;

    @Autowired
    BikeFeingClient bikeFeingClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveCar(UserDto userDto) {
        User user = User.builder().name(userDto.getName()).email(userDto.getEmail()).build();

        User nuevo = userRepository.save(user);

        return nuevo;
    }

    public List<Car> getCars(int userId) {

        return restTempate.exchange(
                "http://car-service:8002/car/byuser/" + userId,
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                new ParameterizedTypeReference<List<Car>>() {
                }).getBody();
    }

    public List<Bike> getBikes(int userId) {

        return restTempate.exchange(
                "http://bike-service:8003/bike/byuser/" + userId,
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                new ParameterizedTypeReference<List<Bike>>() {
                }).getBody();
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);

        Car nuevo = carFeingClient.save(car);

        return nuevo;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);

        Bike nuevo = bikeFeingClient.save(bike);

        return nuevo;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String , Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            result.put("mensaje", String.format("no existe el usuario %s", userId));

            return result;
        }

        result.put("User", user);
        result.put("Cars", String.format("el usuario %s no posee cars", userId));
        result.put("Bikes", String.format("el usuario %s no posee bikes", userId));

        List<Car> cars = carFeingClient.getCars(userId);
        List<Bike> bikes = bikeFeingClient.getBikes(userId);

        if (!cars.isEmpty()) {
            result.put("Cars", cars);
        }
        if (!bikes.isEmpty()) {
            result.put("Bikes", bikes);
        }

        return result;
    }
}
