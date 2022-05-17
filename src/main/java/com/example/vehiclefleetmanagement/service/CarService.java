package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.CarAddForm;
import com.example.vehiclefleetmanagement.domain.CarDetailsDto;
import com.example.vehiclefleetmanagement.domain.RefuelingDto;
import com.example.vehiclefleetmanagement.domain.RepairDto;
import com.example.vehiclefleetmanagement.model.Car;
import com.example.vehiclefleetmanagement.model.User;
import com.example.vehiclefleetmanagement.repository.CarRepository;
import com.example.vehiclefleetmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    public void addCar(CarAddForm carAddForm) {
        Car car = new Car();
        car.setCarBrand(carAddForm.getCarBrand());
        car.setCarModel(carAddForm.getCarModel());
        car.setYear(carAddForm.getYear());
        User user = userRepository.getById(carAddForm.getUserId());
        car.setUser(user);
        carRepository.save(car);
    }

    public CarDetailsDto getCarDetailsById(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        CarDetailsDto carDetailsDto = new CarDetailsDto();
        carDetailsDto.setCarBrand(car.getCarBrand());
        carDetailsDto.setCarModel(car.getCarModel());
        carDetailsDto.setYear(car.getYear());
        carDetailsDto.setUserId(car.getUser().getId());
        carDetailsDto.setRefuelingList(car.getRefuelingList().stream()
                .map(refueling -> new RefuelingDto(refueling.getCreateTime(), refueling.getPrice(), refueling.getLiters()))
                .collect(Collectors.toList()));
        carDetailsDto.setRepairList(car.getRepairList().stream()
                .map(repair -> new RepairDto(repair.getCreateTime(), repair.getPrice(), repair.getDescriptions()))
                .collect(Collectors.toList()));
        return carDetailsDto;
    }
}
