package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import java.util.List;

@Data
public class CompanyStatisticDto {
    private Integer numberOfCars;
    private Integer numberOfUser;
    private List<StatisticCarDto> statisticCarDtoList;
}
