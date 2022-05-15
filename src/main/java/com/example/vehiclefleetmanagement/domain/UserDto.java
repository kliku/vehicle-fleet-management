package com.example.vehiclefleetmanagement.domain;

import com.example.vehiclefleetmanagement.model.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private UserRole userRole;
    private Long companyId;
}
