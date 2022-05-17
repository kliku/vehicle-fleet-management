package com.example.vehiclefleetmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "refuelings")
@Data
public class Refueling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "liters", nullable = false)
    private Double liters;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
