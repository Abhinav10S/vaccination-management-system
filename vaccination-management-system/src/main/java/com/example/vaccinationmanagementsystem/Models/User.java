package com.example.vaccinationmanagementsystem.Models;

import com.example.vaccinationmanagementsystem.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId ;

    @Column(name = "user_name")
    private String name ;

    private int age ;

    private Gender gender;

    private String mobileNo;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Column(unique = true)
    private String emailId ;
    @JsonIgnore
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    private Dose dose ;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Dose getDose() {
        return dose;
    }

    public void setDose(Dose dose) {
        this.dose = dose;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    List<Appointment> appointmentList = new ArrayList<>() ;

}
