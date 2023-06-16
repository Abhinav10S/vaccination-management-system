package com.example.vaccinationmanagementsystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="appointments")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    private Date appointmentDate ;

    private LocalTime appointmentTime ;


    @ManyToOne
    @JoinColumn
    private Doctor doctor ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user ;
}
