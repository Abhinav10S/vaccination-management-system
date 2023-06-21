package com.example.vaccinationmanagementsystem.Services;

import com.example.vaccinationmanagementsystem.Dtos.AssociateDocDto;
import com.example.vaccinationmanagementsystem.Dtos.DoctorUpdateRequestDto;
import com.example.vaccinationmanagementsystem.Enums.Gender;
import com.example.vaccinationmanagementsystem.Exceptions.*;
import com.example.vaccinationmanagementsystem.Models.Doctor;
import com.example.vaccinationmanagementsystem.Models.VaccinationCenter;
import com.example.vaccinationmanagementsystem.Reoository.DoctorRepository;
import com.example.vaccinationmanagementsystem.Reoository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository  ;
    @Autowired
    VaccinationCenterRepository centerRepository  ;
    public String addDoctor(Doctor doctor) throws EmailIdEmptyException , DoctorAlreadyExistException {
      if (doctor.getEmailId()==null){
          throw new EmailIdEmptyException("EmailId Not Found");
      }
        if(doctorRepository.findByEmailId(doctor.getEmailId())!= null){
            throw new DoctorAlreadyExistException("Doctor with this emailId already exits.");
        }

        doctorRepository.save(doctor);
        return "Doctor is added Successfully" ;
    }

    public String associateDoctor(AssociateDocDto associateDocDto) throws DoctorNotFound, CenterNotFound , AlreadyAssociated{

        Integer docId = associateDocDto.getDocId() ;
        //  qInteger centerId = associateDocDto.getCenterId();

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

        if(doctor.getVaccinationCenter() != null ) {
            throw new AlreadyAssociated (doctor.getVaccinationCenter().getAddress());
        }
        doctor.setVaccinationCenter(vaccinationCenter);
        vaccinationCenter.getDoctorList().add(doctor);

        centerRepository.save(vaccinationCenter);
        return "Doctor has been associated to center";
    }

    public List<String> getListOfDoctor(int minAppointmentCount) throws DoctorNotFound {
        List<String> ans = new ArrayList<>()  ;
        List<Doctor> list = doctorRepository.findAll() ;

        for (Doctor doctor : list){
            if (doctor.getAppointmentList().size()>minAppointmentCount){
                ans.add(doctor.getName()) ;
            }
        }
        if (ans == null){
            throw new DoctorNotFound("None of doctor have more than : " +""+minAppointmentCount+"count") ;
        }
        return ans ;
    }

    public List<String> getListOfMaleDoctor() {
        List<String> MaleDocList = new ArrayList<>() ;
        List<Doctor> list = doctorRepository.findAll() ;

        for (Doctor doctor : list){
            if (doctor.getAge()>40 && doctor.getGender().equals(Gender.MALE)){
                MaleDocList.add(doctor.getName());
            }
        }
        if(MaleDocList == null){
            return null ;
        }
        return MaleDocList ;
    }

    public String getRatio() {
        int ans = 0 ;
        List<Doctor> doctorList = doctorRepository.findAll() ;
        int  maleDoc = 0 ;
        int femaleDoc = 0 ;

        for (Doctor doctor : doctorList){
            if (doctor.getGender().equals(Gender.MALE)){
                maleDoc++  ;
            } else if (doctor.getGender().equals(Gender.FEMALE)) {
                femaleDoc++ ;
            }
        }
        int gcd = 1;
        for(int i = 1; i <= maleDoc && i <= femaleDoc; i++){
            if(femaleDoc%i==0 && femaleDoc%i==0) {
                gcd = i;
            }
        }
        int maleDocRatio = maleDoc / gcd ;
        int femaleDocRatio = femaleDoc / gcd ;

        return maleDocRatio+":"+femaleDocRatio ;

    }

    public String updateDoctorByEmailId(DoctorUpdateRequestDto doctorUpdateRequestDto) throws CenterIdNotFound , DoctorNotFound{

        String doctorEmailId = doctorUpdateRequestDto.getEmailID() ;
        Optional<Doctor> doctorOptional = Optional.ofNullable(doctorRepository.findByEmailId(doctorEmailId));
        if (doctorOptional.isEmpty()){
            throw new DoctorNotFound("Doctor with emailID : "+doctorEmailId);
        }

        int centerId = doctorUpdateRequestDto.getCenterID();
        Optional<VaccinationCenter> optionalVaccinationCenter = centerRepository.findById(centerId);
        if (optionalVaccinationCenter.isEmpty()){
            throw new CenterIdNotFound("centerId : "+centerId+" "+"Not Found") ;
        }

        Doctor doctor = doctorOptional.get();
        VaccinationCenter vaccinationCenter = optionalVaccinationCenter.get() ;

        doctor.setVaccinationCenter(vaccinationCenter);

        vaccinationCenter.getDoctorList().add(doctor) ;
        centerRepository.save(vaccinationCenter);

        return "Doctor "+doctor.getName()+" shifted to: "+vaccinationCenter.getCenterName();
    }
}
