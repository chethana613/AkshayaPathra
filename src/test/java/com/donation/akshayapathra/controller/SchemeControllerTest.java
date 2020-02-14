package com.donation.akshayapathra.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.donation.akshayapathra.dto.AnalysisResponseDto;
import com.donation.akshayapathra.dto.UserSchemeDto;
import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.service.SchemeService;
import com.donation.akshayapathra.service.UserSchemeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SchemeControllerTest {

	@InjectMocks
	SchemeController schemeController;

	@Mock
	SchemeService schemeService;

	@Mock
	UserSchemeService userSchemeService;

	Scheme scheme = new Scheme();

	@Before
	public void setUp() {
		scheme.setSchemeId(1);
		scheme.setAmount(20000);
		scheme.setDescription("for Cancer");
		scheme.setImageUrl("https://www.worldvision.in/CMSAdmin/Uploads/06012020120226921Website-article.jpg");
		scheme.setSchemeName("Medical");
		scheme.setTaxBenefitDescription("you will get under 80c");
		scheme.setTaxBenefitAmount(5000);

	}

	@Test
	public void availableSchemesTest() throws SchemeNotFoundException {
		List<Scheme> schemes = new ArrayList<Scheme>();
		schemes.add(scheme);
		Mockito.when(schemeService.viewAllDonations()).thenReturn(schemes);
		ResponseEntity<List<Scheme>> actual = schemeController.viewAllDonations();
		assertNotNull(actual);
	}
	@Test
	public void getAnalysis() {
		ResponseEntity<List<AnalysisResponseDto>> response=schemeController.getAnalysis();
		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
	}

	@Test
	public void testGetUserSchemes() throws SchemeNotFoundException {
		Mockito.when(schemeService.getUserSchemes(1)).thenReturn(new ArrayList<>());
		ResponseEntity<List<UserSchemeDto>> actual = schemeController.getUserSchemes(1);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

	@Test(expected = SchemeNotFoundException.class)
	public void testGetUserSchemesForSchemeNotFoundException() throws SchemeNotFoundException {
		scheme.setSchemeId(null);
		Mockito.when(schemeService.getUserSchemes(1)).thenReturn(new ArrayList<>());
		schemeController.getUserSchemes(null);
	}

}
