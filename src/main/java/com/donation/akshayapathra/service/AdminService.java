package com.donation.akshayapathra.service;

import com.donation.akshayapathra.dto.LoginRequestDto;
import com.donation.akshayapathra.dto.LoginResponseDto;
import com.donation.akshayapathra.exception.AdminNotFoundException;

public interface AdminService {

	LoginResponseDto authenticateAdmin(LoginRequestDto loginRequestDto) throws AdminNotFoundException;

}
