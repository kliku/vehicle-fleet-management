package com.example.vehiclefleetmanagement.domain;




import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public abstract class MaintenanceDto {
    private LocalDateTime createTime;
    private Double price;
    private String descriptions;

    public MaintenanceDto(LocalDateTime createTime, Double price) {
        this.createTime = createTime;
        this.price = price;
    }

    public MaintenanceDto(LocalDateTime createTime, Double price, String descriptions) {
        this.createTime = createTime;
        this.price = price;
        this.descriptions = descriptions;
    }
}
