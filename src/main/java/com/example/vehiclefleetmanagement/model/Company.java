package com.example.vehiclefleetmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    @Column(name = "nip")
    private String nip;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<User> userList;
}
