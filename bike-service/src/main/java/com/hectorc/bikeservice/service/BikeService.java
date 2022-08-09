package com.hectorc.bikeservice.service;


import com.hectorc.bikeservice.dto.BikeDto;
import com.hectorc.bikeservice.entity.Bike;

import java.util.List;

public interface BikeService {

    public List<Bike> getAll();

    public Bike getBikeById(int id);

    public Bike save(BikeDto bikeDto);

    public List<Bike> byUserId(int userId);

}
