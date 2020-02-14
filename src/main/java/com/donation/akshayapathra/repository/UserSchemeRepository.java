package com.donation.akshayapathra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.entity.UserScheme;

@Repository
public interface UserSchemeRepository extends JpaRepository<UserScheme, Integer>{
	
	List<UserScheme> findAllBySchemeId(Scheme schemeId);

}
