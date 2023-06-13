package com.example.vaccinationmanagementsystem.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Table(name="appointments")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    private Data appointmentDate ;

    private Time appointmentTime ;

    @ManyToOne
    @JoinColumn
    private Doctor doctor ;

    @ManyToOne
    @JoinColumn
    private User user ;
}
