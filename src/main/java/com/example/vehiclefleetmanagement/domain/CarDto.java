package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarDto {

    private String carBrand;
    private String carModel;
    private Integer year;
    private Long userId;

}
