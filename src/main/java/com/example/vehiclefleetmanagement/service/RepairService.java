package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.RepairAddForm;
import com.example.vehiclefleetmanagement.model.Car;
import com.example.vehiclefleetmanagement.model.Repair;
import com.example.vehiclefleetmanagement.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    public void addRepair(RepairAddForm repairAddForm) {
        Repair repair = new Repair();
        repair.setCreateTime(LocalDateTime.now());
        repair.setPrice(repairAddForm.getPrice());
        repair.setDescriptions(repairAddForm.getDescriptions());
        Car car = new Car();
        car.setId(repairAddForm.getCarId());
        repair.setCar(car);
        repairRepository.save(repair);
    }
}
