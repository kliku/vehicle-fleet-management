package com.example.vehiclefleetmanagement.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CompanyAddForm {

    @NotBlank
    private String companyName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 10, max = 10)
    private String nip;

}
