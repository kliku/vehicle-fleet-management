package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.*;
import com.example.vehiclefleetmanagement.exceptions.ApplicationLogicExceptions;
import com.example.vehiclefleetmanagement.model.Car;
import com.example.vehiclefleetmanagement.model.User;
import com.example.vehiclefleetmanagement.repository.CarRepository;
import com.example.vehiclefleetmanagement.repository.UserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

    public CarDetailsDto getCarDetailsById(Long id) throws ApplicationLogicExceptions {
        Car car = carRepository.findById(id).orElseThrow(() -> new ApplicationLogicExceptions("Not find id: " + id));
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
        carDetailsDto.setOverviewList(car.getOverviewList().stream()
                .map(overview -> new OverviewDto(overview.getCreateTime(), overview.getPrice(), overview.getDescriptions()))
                .collect(Collectors.toList()));
        return carDetailsDto;
    }

    public List<CarDto> getCarList() {
        List<Car> carList = carRepository.findAll();
        List<CarDto> resultList = new ArrayList<>();
        for (Car car : carList) {
            CarDto carDto = new CarDto();
            carDto.setCarBrand(car.getCarBrand());
            carDto.setCarModel(car.getCarModel());
            carDto.setYear(car.getYear());
            carDto.setUserId(car.getId());
            resultList.add(carDto);
        }
        return resultList;
    }

    public void saveCarFromCsv(Long id, MultipartFile multipartFile) {
        User user = new User();
        user.setId(id);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
            List<Car> carList = new ArrayList<>();
            csvParser.getRecords().stream().forEach(row -> {
               Car car = new Car();
               car.setCarBrand(row.get("carBrand"));
               car.setCarModel(row.get("carModel"));
               car.setYear(Integer.parseInt(row.get("year")));
               car.setUser(user);
               carList.add(car);
            });
            carRepository.saveAll(carList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
