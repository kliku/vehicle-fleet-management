package com.example.vehiclefleetmanagement.repository;

import com.example.vehiclefleetmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
