package com.donation.akshayapathra.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.donation.akshayapathra.dto.LoginRequestDto;
import com.donation.akshayapathra.dto.LoginResponseDto;
import com.donation.akshayapathra.entity.Admin;
import com.donation.akshayapathra.exception.AdminNotFoundException;
import com.donation.akshayapathra.repository.AdminRepository;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
	
	@InjectMocks
	AdminServiceImpl adminServiceImpl;
	
	@Mock
	AdminRepository adminRepository;
	
	LoginRequestDto loginRequestDto = new LoginRequestDto();
	LoginResponseDto loginResponseDto = new LoginResponseDto();
	Admin admin = new Admin();
	
	@Before
	public void setUp() {
		admin.setAdminId(1);
		admin.setMobile(1L);
		admin.setPassword("sri");
		loginRequestDto.setMobile(1L);
		loginRequestDto.setPassword("sri");
		loginResponseDto.setAdminId(1);
	}
	
	@Test
	public void testAuthenticateAdmin() throws AdminNotFoundException {
		Mockito.when(adminRepository.findByMobileAndPassword(1L, "sri")).thenReturn(Optional.of(admin));
		LoginResponseDto actual = adminServiceImpl.authenticateAdmin(loginRequestDto);
		assertEquals(1, actual.getAdminId());
	}
	
	@Test
	public void testAuthenticateAdminForAdminNotFoundException() throws AdminNotFoundException {
		loginRequestDto.setMobile(2L);
		Mockito.when(adminRepository.findByMobileAndPassword(2L, "sri")).thenReturn(Optional.of(admin));
	      adminServiceImpl.authenticateAdmin(loginRequestDto);
	}
	


}
