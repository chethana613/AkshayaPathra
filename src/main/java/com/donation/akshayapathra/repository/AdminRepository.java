package com.donation.akshayapathra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donation.akshayapathra.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
