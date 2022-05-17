package com.example.vehiclefleetmanagement.domain;

import lombok.Data;
import java.util.List;

@Data

public class CarDetailsDto {

    private String carBrand;
    private String carModel;
    private Integer year;
    private List<RefuelingDto> refuelingList;
    private List<RepairDto> repairList;
    private Long userId;
}
