package com.example.vehiclefleetmanagement.cotroler;

import com.example.vehiclefleetmanagement.domain.CompanyAddForm;
import com.example.vehiclefleetmanagement.domain.CompanyDto;
import com.example.vehiclefleetmanagement.domain.CompanyStatisticDto;
import com.example.vehiclefleetmanagement.domain.StatisticCarDto;
import com.example.vehiclefleetmanagement.exceptions.ApplicationLogicExceptions;
import com.example.vehiclefleetmanagement.service.CompanyService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
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

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.ok("Delete company with id:" + id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Not find company id: " + id);
        }
    }

    @GetMapping(value = "/companyList")
    public List<CompanyDto> getCompanyList() {
        return companyService.getCompanyList();
    }

    @GetMapping(value = "/company/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) throws ApplicationLogicExceptions {
        return companyService.getCompanyById(id);
    }

    @GetMapping(value = "/statistic/{id}")
    public CompanyStatisticDto getCompanyStatisticDto(Long id) {
        return companyService.getCompanyStatistic(id);
    }

    @GetMapping(value = "/statisticCsv/{id}")
    public  ResponseEntity<Resource> getCsv(Long id) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=csttest.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(companyService.getCarListStatistic(id)));
    }

}