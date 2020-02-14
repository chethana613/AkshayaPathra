package com.donation.akshayapathra.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.donation.akshayapathra.dto.DonateRequestDto;
import com.donation.akshayapathra.dto.DonateResponseDto;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.service.UserSchemeService;

@RunWith(MockitoJUnitRunner.class)
public class UserSchemeControllerTest {
	@InjectMocks
	UserSchemeController userSchemeController;
	
	@Mock
	UserSchemeService userSchemeService;
	
	DonateResponseDto donateResponseDto= new DonateResponseDto();
	DonateRequestDto donateRequestDto= new DonateRequestDto();
	@Before
	public void init() {
		
	}

	@Test
	public void testDonate() throws SchemeNotFoundException {
		Mockito.when(userSchemeService.donatePayment(donateRequestDto)).thenReturn(donateResponseDto);
		ResponseEntity<DonateResponseDto> response=userSchemeController.donatePayment(donateRequestDto);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
	}
}
