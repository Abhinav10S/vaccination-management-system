package com.example.vaccinationmanagementsystem.Services;

import com.example.vaccinationmanagementsystem.Dtos.UpdateEmailDto;
import com.example.vaccinationmanagementsystem.Enums.Gender;
import com.example.vaccinationmanagementsystem.Models.Dose;
import com.example.vaccinationmanagementsystem.Models.User;
import com.example.vaccinationmanagementsystem.Reoository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository ;
    public String addUser(User user) {
       userRepository.save(user);
       return "user is added" ;
    }

    public Date getVaccDate(Integer userId) {
        User user = userRepository.findById(userId).get();
        Dose dose = user.getDose();

        return dose.getVaccinationDate();
    }
    public String updateEmailId (UpdateEmailDto updateEmailDto){
        int userId = updateEmailDto.getUserId() ;
        User user = userRepository.findById(userId).get();

        user.setEmailId(updateEmailDto.getNewEmailId());
        userRepository.save(user);

        return "Old Email has been modified with new one "+updateEmailDto.getNewEmailId();
    }

    public User getUserByEmail(String emailId) {
        User user = userRepository.findByEmailId(emailId);
        return user ;

    }
}
