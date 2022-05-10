package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.CompanyAddForm;
import com.example.vehiclefleetmanagement.model.Company;
import com.example.vehiclefleetmanagement.repository.CompanyRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Long addCompany(CompanyAddForm companyAddForm) {
        Company company = new Company();
        company.setCompanyName(companyAddForm.getCompanyName());
        company.setEmail(companyAddForm.getEmail());
        company.setNip(companyAddForm.getNip());
        company.setCreateDate(LocalDateTime.now());
        companyRepository.save(company);
        return company.getId();
    }

    public Long addCompanyByNip(String nip) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String url = "https://wl-api.mf.gov.pl/api/search/nip/" + nip +
                "?date=" + LocalDate.now().format(formatter);
        ResponseEntity<String> exchange = restTemplate.exchange(url,
                HttpMethod.GET, HttpEntity.EMPTY, String.class);
        String jsonString = exchange.getBody();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject result = (JSONObject) jsonObject.get("result");
        JSONObject subject = (JSONObject) result.get("subject");
        String name = subject.get("name").toString();

        Company company = new Company();
        company.setCreateDate(LocalDateTime.now());
        company.setCompanyName(name);
        company.setNip(nip);
        company.setEmail("string@string.pl");
        companyRepository.save(company);
        return company.getId();
    }
}
