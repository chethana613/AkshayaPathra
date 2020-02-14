package com.donation.akshayapathra.service;

import com.donation.akshayapathra.dto.DonateRequestDto;
import com.donation.akshayapathra.dto.DonateResponseDto;
import com.donation.akshayapathra.exception.SchemeNotFoundException;

public interface UserSchemeService {
	public DonateResponseDto donatePayment(DonateRequestDto donateRequestDto) throws SchemeNotFoundException ;
		
}
