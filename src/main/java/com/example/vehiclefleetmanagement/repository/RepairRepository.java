package com.example.vehiclefleetmanagement.repository;

import com.example.vehiclefleetmanagement.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
}
