package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefuelingAddForm {

    @NotBlank
    private Double price;
    @NotBlank
    private Double liters;
    private Long carId;
}
