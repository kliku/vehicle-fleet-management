package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.FaultAddForm;
import com.example.vehiclefleetmanagement.model.Car;
import com.example.vehiclefleetmanagement.model.Fault;
import com.example.vehiclefleetmanagement.repository.FaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FaultService {

    @Autowired
    private FaultRepository faultRepository;

    public void addFault(FaultAddForm faultAddForm) {
        Fault fault = new Fault();
        fault.setCreateTime(LocalDateTime.now());
        fault.setPrice(faultAddForm.getPrice());
        fault.setDescriptions(faultAddForm.getDescriptions());
        fault.setIsRepaired(faultAddForm.getIsRepaired());
        Car car = new Car();
        car.setId(faultAddForm.getCarId());
        fault.setCar(car);
        faultRepository.save(fault);
    }
}
