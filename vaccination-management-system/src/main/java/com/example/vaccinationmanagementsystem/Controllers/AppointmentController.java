package com.example.vaccinationmanagementsystem.Controllers;

import com.example.vaccinationmanagementsystem.Dtos.AppointmentReqDto;
import com.example.vaccinationmanagementsystem.Exceptions.DoctorNotFound;
import com.example.vaccinationmanagementsystem.Exceptions.UserNotFound;
import com.example.vaccinationmanagementsystem.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
@Autowired
AppointmentService appointmentService  ;
@PostMapping("/book")
    public ResponseEntity<String> bookAppointment( @RequestBody AppointmentReqDto appointmentReqDto){
        try {
            appointmentService.bookAppointment(appointmentReqDto);
            return new ResponseEntity<>("Appointment is booked Successfully ", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
