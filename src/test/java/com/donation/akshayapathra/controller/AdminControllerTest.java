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

import com.donation.akshayapathra.dto.LoginRequestDto;
import com.donation.akshayapathra.dto.LoginResponseDto;
import com.donation.akshayapathra.exception.AdminNotFoundException;
import com.donation.akshayapathra.service.AdminService;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
	
	@InjectMocks
	AdminController adminController;
	
	@Mock
	AdminService adminService;
	
	LoginRequestDto loginRequestDto = new LoginRequestDto();
	LoginResponseDto loginResponseDto = new LoginResponseDto();
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testAuthenticateAdmin() throws AdminNotFoundException {
		Mockito.when(adminService.authenticateAdmin(loginRequestDto)).thenReturn(loginResponseDto);
		ResponseEntity<LoginResponseDto> actual = adminController.authenticateAdmin(loginRequestDto);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

}
