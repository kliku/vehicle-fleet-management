package com.example.vehiclefleetmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "faults")
@Data
public class Fault {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "descriptions")
    private String descriptions;
    @Column(name = "is_repaired")
    private Boolean isRepaired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
