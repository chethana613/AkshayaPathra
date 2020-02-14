package com.donation.akshayapathra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.akshayapathra.dto.DonateRequestDto;
import com.donation.akshayapathra.dto.DonateResponseDto;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.service.UserSchemeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userschemes")
@CrossOrigin
@Slf4j
public class UserSchemeController {

	@Autowired
	UserSchemeService userSchemeService;
	
	@PostMapping
	public ResponseEntity<DonateResponseDto> donatePayment(@RequestBody DonateRequestDto donateRequestDto) throws SchemeNotFoundException{
		log.info("Entering into donatePayment of UserSchemeController");
		DonateResponseDto donateResponseDto=userSchemeService.donatePayment(donateRequestDto);
		donateResponseDto.setStatusCode(HttpStatus.OK.value());
		donateResponseDto.setStatusMessage("Success");
		return new ResponseEntity<>(donateResponseDto,HttpStatus.OK);
	}
}
