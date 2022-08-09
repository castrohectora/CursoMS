package com.hectorc.carservice.repository;

import com.hectorc.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    public List<Car> findByUserId(int userId);
}
