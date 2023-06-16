package com.example.vaccinationmanagementsystem.Reoository;

import com.example.vaccinationmanagementsystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    public User findByEmailId(String emailId) ;
}
