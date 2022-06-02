package com.example.vehiclefleetmanagement.cotroler;

import com.example.vehiclefleetmanagement.domain.RepairAddForm;
import com.example.vehiclefleetmanagement.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("repair")
public class RepairControler {
    @Autowired
    private RepairService repairService;

    @PostMapping(value = "/addRepair")
    public void addRepair(@RequestBody RepairAddForm repairAddForm) {
        repairService.addRepair(repairAddForm);
    }
}
