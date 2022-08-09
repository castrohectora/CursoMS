package com.hectorc.bikeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BikeDto {

    private String brand;
    private String model;
    private int userId;
}
