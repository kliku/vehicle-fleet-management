package com.example.vehiclefleetmanagement.service;

import com.example.vehiclefleetmanagement.domain.*;
import com.example.vehiclefleetmanagement.exceptions.ApplicationLogicExceptions;
import com.example.vehiclefleetmanagement.model.*;
import com.example.vehiclefleetmanagement.repository.CompanyRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public List<CompanyDto> getCompanyList() {
        List<Company> companyList = companyRepository.findAll();
        List<CompanyDto> resultList = new ArrayList<>();
        for (Company company : companyList) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setCompanyName(company.getCompanyName());
            companyDto.setEmail(company.getEmail());
            companyDto.setNip(company.getNip());
            resultList.add(companyDto);
        }
        return resultList;
    }

    public CompanyDto getCompanyById(Long id) throws ApplicationLogicExceptions{
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ApplicationLogicExceptions("Not find Company by id: " + id));
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setEmail(company.getEmail());
        companyDto.setNip(company.getNip());
        return companyDto;
    }

    public List<StatisticCarDto> getStatisticCar(Company company) {
        List<StatisticCarDto> statisticCarList = new ArrayList<>();
        for (User user : company.getUserList()) {
            for (Car car : user.getCarList()) {
                StatisticCarDto statisticCarDto = new StatisticCarDto();
                statisticCarDto.setCarModel(car.getCarModel());
                statisticCarDto.setCarBrand(car.getCarBrand());
                statisticCarDto.setUserName(user.getUserName());
                List<Overview> overviewList = car.getOverviewList();
                if (!overviewList.isEmpty()) {
                    Overview overview = overviewList.get(overviewList.size() - 1);
                    statisticCarDto.setNextOverview(overview.getValidDate());
                    statisticCarDto.setIsValid(overview.getIsValid());
                }
                statisticCarDto.setCostRefueling(car.getRefuelingList()
                        .stream().mapToDouble(Refueling::getPrice).sum());
                statisticCarDto.setCostRepair(car.getRepairList()
                        .stream().mapToDouble(Repair::getPrice).sum());
                statisticCarList.add(statisticCarDto);
            }
        }
        return statisticCarList;
    }

    public CompanyStatisticDto getCompanyStatistic(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        CompanyStatisticDto companyStatisticDto = new CompanyStatisticDto();
        companyStatisticDto.setStatisticCarDtoList(getStatisticCar(company));
        companyStatisticDto.setNumberOfUser(company.getUserList().size());
        companyStatisticDto.setNumberOfCars(company.getUserList().stream().mapToInt(user ->
                user.getCarList().size()).sum());
        return companyStatisticDto;
    }

    public ByteArrayInputStream getCarListStatistic(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        List<StatisticCarDto> statisticCarList = getStatisticCar(company);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader
                ("carBrand", "carModel", "userName", "nextOverview", "isValid", "costRefueling", "costRepair");
        try (CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), csvFormat)) {
            for (StatisticCarDto statisticCarDto : statisticCarList) {
                csvPrinter.printRecord(statisticCarDto.getCarBrand(),
                        statisticCarDto.getCarModel(), statisticCarDto.getUserName(), LocalDateTime.now(),
                        statisticCarDto.getIsValid() ,statisticCarDto.getCostRefueling(), statisticCarDto.getCostRepair());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }


}
