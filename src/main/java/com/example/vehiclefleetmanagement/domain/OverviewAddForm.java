package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class OverviewAddForm {

    @NotBlank
    private LocalDateTime createTime;
    @NotBlank
    private Double price;
    private String descriptions;
    private LocalDateTime validDate;
    private Boolean isValid;
    private Long carId;
}
