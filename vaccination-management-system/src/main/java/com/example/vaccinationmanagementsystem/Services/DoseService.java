package com.example.vaccinationmanagementsystem.Services;

import com.example.vaccinationmanagementsystem.Models.Dose;
import com.example.vaccinationmanagementsystem.Models.User;
import com.example.vaccinationmanagementsystem.Reoository.DoseRepository;
import com.example.vaccinationmanagementsystem.Reoository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository ;
    @Autowired
    UserRepository userRepository ;
    public String giveDose(String doseId, Integer userId) {
        User user = userRepository.findById(userId).get();

        Dose dose = new Dose () ;

        dose.setDoseId(doseId);
        dose.setUser(user);

        user.setDose(dose);
        userRepository.save(user);

        return "Dose Given to user successfully" ;
    }
}
