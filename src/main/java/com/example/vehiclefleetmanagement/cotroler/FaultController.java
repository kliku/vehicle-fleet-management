package com.example.vehiclefleetmanagement.cotroler;

import com.example.vehiclefleetmanagement.domain.FaultAddForm;
import com.example.vehiclefleetmanagement.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fault")
public class FaultController {

    @Autowired
    private FaultService faultService;

    @PostMapping(value = "/addFault")
    public void addFault(@RequestBody FaultAddForm faultAddForm) {
        faultService.addFault(faultAddForm);
    }
}
