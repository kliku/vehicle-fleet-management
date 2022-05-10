package com.example.vehiclefleetmanagement.cotroler;

import com.example.vehiclefleetmanagement.domain.CompanyAddForm;
import com.example.vehiclefleetmanagement.service.CompanyService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addCompany(@Valid @RequestBody CompanyAddForm companyAddForm) {
        Long id = companyService.addCompany(companyAddForm);
        return ResponseEntity.ok("Company id: " + id + " is created");
    }

    @PostMapping(value = "/addByNip")
    public ResponseEntity<String> addCompanyByNip(@RequestBody String nip) {
        try {
            Long id = companyService.addCompanyByNip(nip);
            return ResponseEntity.ok("Company id: " + id + " is created");
        } catch (JSONException e) {
             return ResponseEntity.ok("Error when create company");
        }

    }
}