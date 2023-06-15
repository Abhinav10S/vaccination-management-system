package com.example.vaccinationmanagementsystem.Services;

import com.example.vaccinationmanagementsystem.Exceptions.VaccinationAddressNotFound;
import com.example.vaccinationmanagementsystem.Models.VaccinationCenter;
import com.example.vaccinationmanagementsystem.Reoository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {
@Autowired
    VaccinationCenterRepository vaccinationCenterRepository ;
    public String addCenter(VaccinationCenter vaccinationCenter) throws VaccinationAddressNotFound {
        if (vaccinationCenter.getAddress() == null){
            throw new VaccinationAddressNotFound("Vaccination Center is Empty");
        }
        vaccinationCenterRepository.save(vaccinationCenter);

        return "Vaccination center added at a location "+vaccinationCenter.getAddress();
    }
}
