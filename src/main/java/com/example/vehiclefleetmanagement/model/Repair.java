package com.example.vehiclefleetmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "repairs")
@Data
public class Repair {
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
