package com.example.vehiclefleetmanagement.domain;

import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class CarAddForm {

    @NotBlank
    private String carBrand;
    @NotBlank
    private String carModel;
    @NotBlank
    private Integer year;
    private Long userId;
}
