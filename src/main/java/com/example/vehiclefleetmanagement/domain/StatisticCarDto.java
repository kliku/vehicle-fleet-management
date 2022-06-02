package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatisticCarDto {

    private String carBrand;
    private String carModel;
    private String userName;
    private LocalDateTime nextOverview;
    private Boolean isValid;
    private Double costRefueling;
    private Double costRepair;
}
