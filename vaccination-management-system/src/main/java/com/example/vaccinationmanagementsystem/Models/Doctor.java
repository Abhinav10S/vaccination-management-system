package com.example.vaccinationmanagementsystem.Models;

import com.example.vaccinationmanagementsystem.Enums.Gender;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId ;

    private String name ;
    private int age ;

    @Enumerated(EnumType.STRING)
    private Gender gender ;
    @Column(unique = true)
    private String emailID ;

    @ManyToOne
    @JoinColumn
    private VaccinationCenter vaccinationCenter;

    @OneToMany(mappedBy = "doctor" ,cascade = CascadeType.ALL)
    private List<Appointment> appointmentList  = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(int docId, String name, int age, Gender gender, String emailID, VaccinationCenter vaccinationCenter, List<Appointment> appointmentList) {
        this.docId = docId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.emailID = emailID;
        this.vaccinationCenter = vaccinationCenter;
        this.appointmentList = appointmentList;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
