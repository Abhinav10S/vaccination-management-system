package com.example.vaccinationmanagementsystem.Services;

import com.example.vaccinationmanagementsystem.Dtos.AssociateDocDto;
import com.example.vaccinationmanagementsystem.Exceptions.CenterNotFound;
import com.example.vaccinationmanagementsystem.Exceptions.DoctorAlreadyExistException;
import com.example.vaccinationmanagementsystem.Exceptions.DoctorNotFound;
import com.example.vaccinationmanagementsystem.Exceptions.EmailIdEmptyException;
import com.example.vaccinationmanagementsystem.Models.Doctor;
import com.example.vaccinationmanagementsystem.Models.VaccinationCenter;
import com.example.vaccinationmanagementsystem.Reoository.DoctorRepository;
import com.example.vaccinationmanagementsystem.Reoository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository  ;
    @Autowired
    VaccinationCenterRepository centerRepository  ;
    public String addDoctor(Doctor doctor) throws EmailIdEmptyException , DoctorAlreadyExistException {
      if (doctor.getEmailID()==null){
          throw new EmailIdEmptyException("EmailId Not Found");
      }
        if(doctorRepository.findByEmailId(doctor.getEmailID())!= null){
            throw new DoctorAlreadyExistException("Doctor with this emailId already exits.");
        }

        doctorRepository.save(doctor);
        return "Doctor is added Successfully" ;
    }

    public String associateDoctor(AssociateDocDto associateDocDto) throws DoctorNotFound, CenterNotFound {

        Integer docId = associateDocDto.getDocId() ;

        Optional<Doctor> doctorOptional = doctorRepository.findById(docId);

        if (!doctorOptional.isPresent()){
            throw new DoctorNotFound("Doctor is Not Found with DocId") ;
        }

        Integer centerId = associateDocDto.getCenterId();

        Optional<VaccinationCenter> optionalVaccinationCenter = centerRepository.findById(centerId);

        if (!optionalVaccinationCenter.isPresent()){
            throw new CenterNotFound("CenterId id is incorrect");
        }

        Doctor doctor = doctorOptional.get();
        VaccinationCenter vaccinationCenter = optionalVaccinationCenter.get();

        doctor.setVaccinationCenter(vaccinationCenter);

        vaccinationCenter.getDoctorList().add(doctor);

        centerRepository.save(vaccinationCenter);

        return "Doctor has been associated to center";
    }
}
