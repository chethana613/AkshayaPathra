package com.donation.akshayapathra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donation.akshayapathra.entity.Scheme;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Integer>{

}
