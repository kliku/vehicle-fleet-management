package com.example.vehiclefleetmanagement.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(name = "car_brand", nullable = false)
    private String carBrand;
    @Column(name = "car_model", nullable = false)
    private String carModel;
    @Column(name = "year", nullable = false)
    private Integer year;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Refueling> refuelingList;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Repair> repairList;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @OrderBy("id")
    private List<Overview> overviewList;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Fault> faultList;

}
