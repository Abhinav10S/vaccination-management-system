package com.example.vaccinationmanagementsystem.Controllers;

import com.example.vaccinationmanagementsystem.Dtos.AssociateDocDto;
import com.example.vaccinationmanagementsystem.Dtos.DoctorUpdateRequestDto;
import com.example.vaccinationmanagementsystem.Models.Doctor;
import com.example.vaccinationmanagementsystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("getListOfDoctorWithMoreThanApp")
    ResponseEntity<List<String>> getListOfDoctor (@RequestParam int minAppointmentCount  ){
        try{
            List<String> doctorList = doctorService.getListOfDoctor(minAppointmentCount) ;
            return new ResponseEntity<>(doctorList  , HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getListOfAllMaleDoctorAbove40")
    public ResponseEntity<List<String>>  getListOfAllMaleDoctor(){
        try{
            List<String> doctorList = doctorService.getListOfMaleDoctor() ;
            return new ResponseEntity<>(doctorList , HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getRatioBetweenMaleAndFemaleDoc")
    public ResponseEntity<String> getRatio (){
           String ans = doctorService.getRatio();
           return  new ResponseEntity<>(ans , HttpStatus.OK);
    }

    @PutMapping("/updateDocDetailsByEmailId")
    public ResponseEntity<String> updateDoctorByEmailId(@RequestBody DoctorUpdateRequestDto doctorUpdateRequestDto) {
        try {
            String result = doctorService.updateDoctorByEmailId(doctorUpdateRequestDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
