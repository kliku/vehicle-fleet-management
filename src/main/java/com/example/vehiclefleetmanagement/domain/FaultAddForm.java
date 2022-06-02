package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FaultAddForm {
    @NotBlank
    private Double price;
    private String descriptions;
    private Boolean isRepaired;
    private Long carId;
}
