package com.example.vaccinationmanagementsystem.Services;

import com.example.vaccinationmanagementsystem.Dtos.AppointmentReqDto;
import com.example.vaccinationmanagementsystem.Exceptions.DoctorNotFound;
import com.example.vaccinationmanagementsystem.Exceptions.UserNotFound;
import com.example.vaccinationmanagementsystem.Models.Appointment;
import com.example.vaccinationmanagementsystem.Models.Doctor;
import com.example.vaccinationmanagementsystem.Models.User;
import com.example.vaccinationmanagementsystem.Reoository.AppointmentRepository;
import com.example.vaccinationmanagementsystem.Reoository.DoctorRepository;
import com.example.vaccinationmanagementsystem.Reoository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository ;
    @Autowired
    DoctorRepository doctorRepository  ;
    @Autowired
    UserRepository userRepository ;
    public String bookAppointment(AppointmentReqDto appointmentReqDto) throws DoctorNotFound , UserNotFound {
         Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentReqDto.getDocId());
         if(doctorOptional == null){
             throw new DoctorNotFound("Doctor Not Found !") ;
         }
         Optional<User> userOptional = userRepository.findById(appointmentReqDto.getUserId());
         if (userOptional == null){
             throw new UserNotFound("User Not Found !") ;
         }

        Doctor doctor = doctorOptional.get();
        User user = userOptional.get();

        Appointment appointment = new Appointment() ;

        appointment.setAppointmentDate(appointmentReqDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentReqDto.getAppointmentTime());

        appointment.setDoctor(doctor);
        appointment.setUser(user);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);


        return "Appointment booked successfully";
    }
}
