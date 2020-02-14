package com.donation.akshayapathra.service;

import java.util.List;

import com.donation.akshayapathra.dto.AnalysisResponseDto;
import com.donation.akshayapathra.dto.UserSchemeDto;
import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;

public interface SchemeService {
	
	List<UserSchemeDto> getUserSchemes(Integer schemeId) throws SchemeNotFoundException;

	public List<Scheme> viewAllDonations() throws SchemeNotFoundException;
	
	public List<AnalysisResponseDto> getAnalysis();

}
