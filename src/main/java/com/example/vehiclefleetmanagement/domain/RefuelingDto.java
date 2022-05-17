package com.example.vehiclefleetmanagement.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class RefuelingDto extends MaintenanceDto{

    private Double liters;

    public RefuelingDto(LocalDateTime createTime, Double price, Double liters) {
        super(createTime, price);
        this.liters = liters;
    }
}
