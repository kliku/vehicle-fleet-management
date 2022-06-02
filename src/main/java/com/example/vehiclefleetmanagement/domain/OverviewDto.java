package com.example.vehiclefleetmanagement.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OverviewDto extends MaintenanceDto {
    private LocalDateTime validDate;
    private Boolean isValid;

    public OverviewDto(LocalDateTime createTime, Double price, String descriptions) {
        super(createTime, price, descriptions);
    }
}
