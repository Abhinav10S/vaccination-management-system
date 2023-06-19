package com.example.vaccinationmanagementsystem.Controllers;

import com.example.vaccinationmanagementsystem.Dtos.UpdateEmailDto;
import com.example.vaccinationmanagementsystem.Models.User;
import com.example.vaccinationmanagementsystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService ;

    @PostMapping("/add")
    public String addUser (@RequestBody User user){
        return userService.addUser(user) ;
    }

    @GetMapping("/getVaccinationDate")
    public Date getVaccinationDate (@RequestParam("userId") int userId){
        return userService.getVaccDate(userId);
    }

    @PutMapping("/updateEmailId")
    public String updateEmailId (UpdateEmailDto updateEmailDto){
        return userService.updateEmailId(updateEmailDto);
    }

    @GetMapping("/getByEmail/{emailId}")
    public User getUserByEmail (@PathVariable ("emailId") String emailId){
        return userService.getUserByEmail(emailId);
    }
}