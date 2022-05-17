package com.example.vehiclefleetmanagement.repository;

import com.example.vehiclefleetmanagement.model.Fault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaultRepository extends JpaRepository<Fault, Long> {
}
