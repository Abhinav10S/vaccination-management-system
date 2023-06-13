package com.example.vaccinationmanagementsystem.Controllers;

import com.example.vaccinationmanagementsystem.Dtos.AssociateDocDto;
import com.example.vaccinationmanagementsystem.Models.Doctor;
import com.example.vaccinationmanagementsystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService ;

    @PostMapping("/add-doctor")
    ResponseEntity<String> addDoctor (@RequestBody Doctor doctor){
        try{
            doctorService.addDoctor(doctor);
            return new ResponseEntity<> ("Doctor is added " , HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/associateWithCenter")
    ResponseEntity<String> associateDoctor(@RequestBody AssociateDocDto associateDocDto){
        try{
            String result = doctorService.associateDoctor(associateDocDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
