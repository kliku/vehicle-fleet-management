package com.example.vehiclefleetmanagement.repository;


import com.example.vehiclefleetmanagement.model.Refueling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefuelingRepository extends JpaRepository<Refueling, Long> {
}
