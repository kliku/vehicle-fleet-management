package com.example.vehiclefleetmanagement.domain;

import com.example.vehiclefleetmanagement.model.enums.UserRole;
import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserAddForm {

    @NotBlank
    private String userName;
    private String password;
    @NotBlank
    @Email
    private String email;
    private UserRole userRole;
    private Long companyId;
}
