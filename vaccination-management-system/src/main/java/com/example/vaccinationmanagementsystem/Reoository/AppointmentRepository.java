package com.example.vaccinationmanagementsystem.Reoository;

import com.example.vaccinationmanagementsystem.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
