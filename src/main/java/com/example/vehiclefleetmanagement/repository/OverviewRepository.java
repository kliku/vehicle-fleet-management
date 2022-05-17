package com.example.vehiclefleetmanagement.repository;

import com.example.vehiclefleetmanagement.model.Overview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverviewRepository extends JpaRepository<Overview, Long> {
}
