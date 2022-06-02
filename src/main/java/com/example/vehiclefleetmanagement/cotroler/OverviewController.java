package com.example.vehiclefleetmanagement.cotroler;

import com.example.vehiclefleetmanagement.domain.OverviewAddForm;
import com.example.vehiclefleetmanagement.service.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/overview")
public class OverviewController {

    @Autowired
    private OverviewService overviewService;

    @PostMapping(value = "/addOverview")
    public void addOverview(@RequestBody OverviewAddForm overviewAddForm) {
        overviewService.addOverview(overviewAddForm);
    }
}
