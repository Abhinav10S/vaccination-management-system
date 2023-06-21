package com.example.vaccinationmanagementsystem.Controllers;

import com.example.vaccinationmanagementsystem.Exceptions.VaccinationAddressNotFound;
import com.example.vaccinationmanagementsystem.Models.Doctor;
import com.example.vaccinationmanagementsystem.Models.VaccinationCenter;
import com.example.vaccinationmanagementsystem.Services.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService ;
    @PostMapping("/add")
    public ResponseEntity<String> addCenter (@RequestBody VaccinationCenter vaccinationCenter){
        try {
            String result = vaccinationCenterService.addCenter(vaccinationCenter);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (VaccinationAddressNotFound e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }




}
