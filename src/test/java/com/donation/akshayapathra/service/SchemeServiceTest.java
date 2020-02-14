package com.donation.akshayapathra.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.donation.akshayapathra.dto.UserSchemeDto;
import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.entity.UserScheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.repository.SchemeRepository;
import com.donation.akshayapathra.repository.UserSchemeRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SchemeServiceTest {

	@Mock
	SchemeRepository schemeRepository;
	
	@InjectMocks
	SchemeServiceImpl schemeServiceImpl;
	
	@Mock
	UserSchemeRepository userSchemeRepository;
	
	Scheme scheme = new Scheme();
	List<Scheme> schemes = new ArrayList<Scheme>();
	UserScheme userScheme = new UserScheme();
	List<UserScheme> userSchemes = new ArrayList<>();
	UserSchemeDto UserSchemeDto = new UserSchemeDto();
	
	@Before
	public void setUp() {
	
		scheme.setAmount(20000);
		scheme.setDescription("for Cancer");
		scheme.setImageUrl("https://www.worldvision.in/CMSAdmin/Uploads/06012020120226921Website-article.jpg");
		scheme.setSchemeName("Medical");
		scheme.setTaxBenefitDescription("you will get under 80c");
		scheme.setTaxBenefitAmount(5000);
		schemes.add(scheme);
		userScheme.setSchemeId(scheme);
		userSchemes.add(userScheme);
	}
	
	@Test
	public void availableSchemesTest() throws SchemeNotFoundException {
		
		Mockito.when(schemeRepository.findAll()).thenReturn(schemes);
		List<Scheme> actual = schemeServiceImpl.viewAllDonations();
		assertNotNull(actual);
		
	}
	
	@Test(expected = SchemeNotFoundException.class)
	public void testSchemeNotFoundException() throws SchemeNotFoundException {
		schemes = new ArrayList<Scheme>();
		schemeServiceImpl.viewAllDonations();
	}
	
	@Test
	public void testGetUserSchemes() throws SchemeNotFoundException {
		Mockito.when(schemeRepository.findById(1)).thenReturn(Optional.of(scheme));
		Mockito.when(userSchemeRepository.findAllBySchemeId(scheme)).thenReturn(userSchemes);
		List<UserSchemeDto> actual = schemeServiceImpl.getUserSchemes(1);
		assertEquals(1, actual.size());
		
	}
}
