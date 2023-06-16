package com.example.vaccinationmanagementsystem.Controllers;

import com.example.vaccinationmanagementsystem.Services.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService ;

    @PostMapping("/giveDose1")
    public String giveDose (@RequestParam("doseId") String doseId , @RequestParam("userId") Integer userId){
        return doseService.giveDose(doseId,userId);
    }

}
