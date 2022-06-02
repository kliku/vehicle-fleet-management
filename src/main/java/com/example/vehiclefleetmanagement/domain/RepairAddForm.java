package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RepairAddForm {
    @NotBlank
    private Double price;
    @NotBlank
    private String descriptions;
    private Long carId;
}
