package com.donation.akshayapathra.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.akshayapathra.constant.Constant;
import com.donation.akshayapathra.dto.DonateRequestDto;
import com.donation.akshayapathra.dto.DonateResponseDto;
import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.entity.User;
import com.donation.akshayapathra.entity.UserScheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.repository.SchemeRepository;
import com.donation.akshayapathra.repository.UserRepository;
import com.donation.akshayapathra.repository.UserSchemeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSchemeImpl implements UserSchemeService{
	@Autowired
	SchemeRepository schemeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSchemeRepository userSchemeRepository;
	
	@Autowired
	PaymentRegistry paymentRegistry;

	@Transactional
	public DonateResponseDto donatePayment(DonateRequestDto donateRequestDto) throws SchemeNotFoundException {
		log.info("Entering into donatePayment of UserSchemeImpl");

		Optional<Scheme> schemeResponse = schemeRepository.findById(donateRequestDto.getSchemeId());
		if (!schemeResponse.isPresent()) {
			log.error("Exception occured in donatePayment of UserSchemeImpl");
			throw new SchemeNotFoundException(Constant.SCHEME_NOT_FOUND);
		}
		
		Optional<User> userResponse=userRepository.findByPanNumber(donateRequestDto.getPanNumber());
		User user= null;
		if(!userResponse.isPresent()) {
			user = new User();
			BeanUtils.copyProperties(donateRequestDto, user);
			userRepository.save(user);
		}
		else 
		user=userResponse.get();
		
		String message=paymentRegistry.getServiceBean(donateRequestDto.getPaymentMode().toString()).payment();

		UserScheme userScheme = new UserScheme();
		userScheme.setPaymentMode(donateRequestDto.getPaymentMode());
		userScheme.setPaymentStatus(message);
		userScheme.setUserId(user);
		userScheme.setSchemeId(schemeResponse.get());
		userScheme.setDate(LocalDate.now());
		userSchemeRepository.save(userScheme);

		DonateResponseDto donateResponseDto = new DonateResponseDto();
		donateResponseDto.setUserId(user.getUserId());
		return donateResponseDto;
	}
}
