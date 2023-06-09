package com.example.vaccinationmanagementsystem.Controllers;

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
    public User addUser (@RequestBody User user){
        return userService.addUser(user) ;
    }

    @GetMapping("/getVaccinationDate")
    public Date getVaccinationDate (@RequestParam("userId") Integer userId){
        return userService.getVaccDate(userId);
    }
}