package com.donation.akshayapathra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donation.akshayapathra.entity.UserScheme;

@Repository
public interface UserSchemeRepository extends JpaRepository<UserScheme, Integer>{

}
