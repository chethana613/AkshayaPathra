package com.donation.akshayapathra.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.akshayapathra.constant.Constant;
import com.donation.akshayapathra.dto.LoginRequestDto;
import com.donation.akshayapathra.dto.LoginResponseDto;
import com.donation.akshayapathra.entity.Admin;
import com.donation.akshayapathra.exception.AdminNotFoundException;
import com.donation.akshayapathra.repository.AdminRepository;
import com.donation.akshayapathra.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;
	
	/**
	 * @author PriyaDharshini S.
	 * @since 2020-02-14.
	 * 
	 *        This method will authenticate the admin.
	 * @param loginDto - details of the admin login
	 * @return LoginResponseDto which has status message,statusCode and adminId.
	 * @throws AdminNotFoundException it will throw the exception if the admin is
	 *                                 not there.
	 * 
	 */
	@Override
	public LoginResponseDto authenticateAdmin(LoginRequestDto loginRequestDto) throws AdminNotFoundException {
		Optional<Admin> admin = adminRepository.findByMobileAndPassword(loginRequestDto.getMobile(),
				loginRequestDto.getPassword());
		if (!admin.isPresent()) {
			log.error("Exception occurred in AdminServiceImpl authenticateAdmin method:" + Constant.ADMIN_NOT_FOUND);
			throw new AdminNotFoundException(Constant.ADMIN_NOT_FOUND);
		}
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setAdminId(admin.get().getAdminId());
		log.info("Entering into AdminServiceImpl authenticateAdmin method: Authentication Successful");
		return loginResponseDto;

	}

}
