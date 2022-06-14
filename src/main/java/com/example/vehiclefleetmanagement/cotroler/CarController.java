package com.example.vehiclefleetmanagement.cotroler;

import com.example.vehiclefleetmanagement.domain.CarAddForm;
import com.example.vehiclefleetmanagement.domain.CarDetailsDto;
import com.example.vehiclefleetmanagement.domain.CarDto;
import com.example.vehiclefleetmanagement.exceptions.ApplicationLogicExceptions;
import com.example.vehiclefleetmanagement.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;


    @PostMapping(value = "/addCar")
    public void addCar(@RequestBody CarAddForm carAddForm) {
        carService.addCar(carAddForm);
    }
    @GetMapping(value = "/details/{id}")
    public CarDetailsDto getCarDetailsById(@PathVariable Long id) {
        try {
            return carService.getCarDetailsById(id);
        } catch (ApplicationLogicExceptions e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value = "/carList")
    public List<CarDto> getCarList() {
        return carService.getCarList();
    }

    @PostMapping(value = "/addCarFromCSV/{id}")
    public void saveCarFromCsv(@PathVariable Long id, @RequestPart("multipartFile") MultipartFile multipartFile) {
        carService.saveCarFromCsv(id, multipartFile);
    }
}
