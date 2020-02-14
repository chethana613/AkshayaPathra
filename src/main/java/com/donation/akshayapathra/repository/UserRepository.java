package com.donation.akshayapathra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donation.akshayapathra.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
