package com.donation.akshayapathra.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.akshayapathra.constant.Constant;
import com.donation.akshayapathra.dto.LoginRequestDto;
import com.donation.akshayapathra.dto.LoginResponseDto;
import com.donation.akshayapathra.exception.AdminNotFoundException;
import com.donation.akshayapathra.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admins")
@CrossOrigin
@Slf4j
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-11. This method will authenticate the admin.
	 * @param loginDto - details of the admin login
	 * @return LoginResponseDto which has status message,statusCode and adminId
	 * @throws AdminNotFoundException it will throw the exception if the admin is
	 *                                 not there.
	 * 
	 */
	@PostMapping
	public ResponseEntity<LoginResponseDto> authenticateAdmin(@Valid @RequestBody LoginRequestDto loginRequestDto)
			throws AdminNotFoundException {
		LoginResponseDto loginResponseDto = adminService.authenticateAdmin(loginRequestDto);
		log.info("Entering into AdminController authenticateAdmin method: calling AdminService");
		loginResponseDto.setStatusMessage(Constant.AUTHENTICATION_SUCCESSFUL);
		loginResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}

}
