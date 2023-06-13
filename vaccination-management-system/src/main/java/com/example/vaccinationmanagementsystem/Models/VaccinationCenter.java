package com.example.vaccinationmanagementsystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="vaccination_center")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String centerName ;

    private LocalTime openingTime ;

    private LocalTime closingTime ;

    private int doseCapacity ;

    @OneToMany(mappedBy = "vaccinationCenter" , cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<>() ;

}
