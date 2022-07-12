package com.example.vehiclefleetmanagement.cotroler;

import com.example.vehiclefleetmanagement.domain.RefuelingAddForm;
import com.example.vehiclefleetmanagement.service.RefuelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refueling")
public class RefuelingController {

    @Autowired
    private RefuelingService refuelingService;

    @PostMapping(value = "/addRefueling")
    public void addRefueling(@RequestBody RefuelingAddForm refuelingAddForm) {
        refuelingService.addRefueling(refuelingAddForm);
    }
}
