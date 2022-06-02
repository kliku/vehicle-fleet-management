package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.OverviewAddForm;
import com.example.vehiclefleetmanagement.model.Car;
import com.example.vehiclefleetmanagement.model.Overview;
import com.example.vehiclefleetmanagement.repository.OverviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OverviewService {

    @Autowired
    private OverviewRepository overviewRepository;


    public void addOverview(OverviewAddForm overviewAddForm) {
        Overview overview = new Overview();
        overview.setCreateTime(LocalDateTime.now());
        overview.setPrice(overviewAddForm.getPrice());
        overview.setDescriptions(overviewAddForm.getDescriptions());
        overview.setValidDate(LocalDateTime.now().plusYears(1));
        overview.setIsValid(overviewAddForm.getIsValid());
        Car car = new Car();
        car.setId(overviewAddForm.getCarId());
        overview.setCar(car);
        overviewRepository.save(overview);
    }
}
