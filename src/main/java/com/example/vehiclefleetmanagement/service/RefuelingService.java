package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.RefuelingAddForm;
import com.example.vehiclefleetmanagement.model.Car;
import com.example.vehiclefleetmanagement.model.Refueling;
import com.example.vehiclefleetmanagement.repository.CarRepository;
import com.example.vehiclefleetmanagement.repository.RefuelingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RefuelingService {
    @Autowired
    private RefuelingRepository refuelingRepository;

    @Autowired
    private CarRepository carRepository;

    public void addRefueling(RefuelingAddForm refuelingAddForm) {
        Refueling refueling = new Refueling();
        refueling.setCreateTime(LocalDateTime.now());
        refueling.setPrice(refuelingAddForm.getPrice());
        refueling.setLiters(refuelingAddForm.getLiters());
    //  Car car = carRepository.getById(refuelingAddForm.getCarId());
        Car car = new Car();
        car.setId(refuelingAddForm.getCarId());
        refueling.setCar(car);
        refuelingRepository.save(refueling);
    }
}
