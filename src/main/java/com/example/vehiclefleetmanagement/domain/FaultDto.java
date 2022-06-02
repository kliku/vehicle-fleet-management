package com.example.vehiclefleetmanagement.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FaultDto extends MaintenanceDto {

    private Boolean isRepaired;

    public FaultDto(LocalDateTime createTime, Double price, String descriptions) {
        super(createTime, price, descriptions);
    }

}
