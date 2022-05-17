package com.example.vehiclefleetmanagement.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class RepairDto extends MaintenanceDto{

    private String descriptions;

    public RepairDto(LocalDateTime createTime, Double price, String descriptions) {
        super(createTime, price);
        this.descriptions = descriptions;
    }
}
