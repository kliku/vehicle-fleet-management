package com.example.vehiclefleetmanagement.repository;

import com.example.vehiclefleetmanagement.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
