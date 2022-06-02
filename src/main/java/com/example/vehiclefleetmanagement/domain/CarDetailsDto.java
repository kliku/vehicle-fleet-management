package com.example.vehiclefleetmanagement.domain;

import lombok.Data;
import java.util.List;

@Data

public class CarDetailsDto extends CarDto{

    private List<RefuelingDto> refuelingList;
    private List<RepairDto> repairList;
    private List<OverviewDto> overviewList;
    private List<FaultDto> faultList;
}
