package com.example.vehiclefleetmanagement.domain;

import lombok.Data;



@Data
public class CompanyDto {

    private Long id;
    private String companyName;
    private String email;
    private String nip;
}
