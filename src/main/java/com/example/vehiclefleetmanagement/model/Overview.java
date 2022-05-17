package com.example.vehiclefleetmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "overviews")
@Data
public class Overview {
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
    @Column(name = "valid_date")
    private LocalDateTime validDate;
    @Column(name = "is_valid")
    private Boolean isValid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
