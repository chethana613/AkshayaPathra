package com.donation.akshayapathra.service;

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

import com.donation.akshayapathra.dto.AnalysisResponseDto;
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
	
	UserScheme userScheme= new UserScheme();
	List<UserScheme> userSchemeList= new ArrayList<>();
	List<UserScheme> userSchemeList1= new ArrayList<>();
	Scheme scheme= new Scheme();
	
	@Before
	public void init() {
		scheme.setSchemeId(1);
		userScheme.setSchemeId(scheme);
		userScheme.setUserSchemeId(1);
		userSchemeList1.add(userScheme);
	}
	
	@Test
	public void availableSchemesTest() throws SchemeNotFoundException {
		Scheme scheme = new Scheme();
		scheme.setAmount(20000);
		scheme.setDescription("for Cancer");
		scheme.setImageUrl("https://www.worldvision.in/CMSAdmin/Uploads/06012020120226921Website-article.jpg");
		scheme.setSchemeName("Medical");
		scheme.setTaxBenefitDescription("you will get under 80c");
		scheme.setTaxBenefitAmount(5000);
		Scheme scheme1 = new Scheme();
		scheme.setAmount(20000);
		scheme.setDescription("for Cancer");
		scheme.setImageUrl("https://www.worldvision.in/CMSAdmin/Uploads/06012020120226921Website-article.jpg");
		scheme.setSchemeName("Medical");
		scheme.setTaxBenefitDescription("you will get under 80c");
		scheme.setTaxBenefitAmount(5000);
		List<Scheme> schemes = new ArrayList<Scheme>();
		schemes.add(scheme);
		schemes.add(scheme1);
		
		Mockito.when(schemeRepository.findAll()).thenReturn(schemes);
		List<Scheme> actual = schemeServiceImpl.viewAllDonations();
		assertNotNull(actual);
		
	}
	
	@Test(expected = SchemeNotFoundException.class)
	public void testSchemeNotFoundException() throws SchemeNotFoundException {
		List<Scheme> schemes = new ArrayList<Scheme>();
		schemes.add(null);
		schemeServiceImpl.viewAllDonations();
	}
	
	@Test
	public void getAnalysisNoList() {
		Mockito.when(userSchemeRepository.findAll()).thenReturn(userSchemeList);
		List<AnalysisResponseDto> userSchemeList=schemeServiceImpl.getAnalysis();
		assertEquals(0, userSchemeList.size());
	}
	
	@Test
	public void getAnalysis() {
		Mockito.when(userSchemeRepository.findAll()).thenReturn(userSchemeList1);
		List<AnalysisResponseDto> userSchemeList=schemeServiceImpl.getAnalysis();
		assertEquals(1, userSchemeList.size());
	}
}
