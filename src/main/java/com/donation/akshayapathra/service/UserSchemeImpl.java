package com.donation.akshayapathra.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
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
import com.donation.akshayapathra.util.SendMail;

import lombok.extern.slf4j.Slf4j;

/**
 * This class provides the services related to the donor scheme related things
 * like donate Payment
 * 
 * @author Chethana M
 * @since 14-Feb-2020
 * @version 1.0
 *
 */
@Service
@Slf4j
@EnableAsync
public class UserSchemeImpl implements UserSchemeService {
	@Autowired
	SchemeRepository schemeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSchemeRepository userSchemeRepository;

	@Autowired
	PaymentRegistry paymentRegistry;

	@Autowired
	SendMail sendMail;

	/**
	 * This method is used to donatePayment for the charity based on any theme
	 * 
	 * @author Chethana M
	 * @param donateRequestDto - This takes in request parameters like donor details
	 *                         and scheme details
	 * @return DonateResponseDto - returns success/failure code along with donation
	 *         details
	 * @throws SchemeNotFoundException- thrown when the requested scheme is not
	 *                                  found or unavailable
	 * @since 14-Feb-2020
	 */
	@Transactional
	public DonateResponseDto donatePayment(DonateRequestDto donateRequestDto) throws SchemeNotFoundException {
		log.info("Entering into donatePayment of UserSchemeImpl");

		Optional<Scheme> schemeResponse = schemeRepository.findById(donateRequestDto.getSchemeId());
		if (!schemeResponse.isPresent()) {
			log.error("Exception occured in donatePayment of UserSchemeImpl");
			throw new SchemeNotFoundException(Constant.SCHEME_NOT_FOUND);
		}

		Optional<User> userResponse = userRepository.findByPanNumber(donateRequestDto.getPanNumber());
		User user = null;
		if (!userResponse.isPresent()) {
			user = new User();
			BeanUtils.copyProperties(donateRequestDto, user);
			userRepository.save(user);
		} else
			user = userResponse.get();

		String message = paymentRegistry.getServiceBean(donateRequestDto.getPaymentMode().toString()).payment();

		UserScheme userScheme = new UserScheme();
		userScheme.setPaymentMode(donateRequestDto.getPaymentMode());
		userScheme.setPaymentStatus(message);
		userScheme.setUserId(user);
		userScheme.setSchemeId(schemeResponse.get());
		userScheme.setDate(LocalDate.now());
		userSchemeRepository.save(userScheme);

		DonateResponseDto donateResponseDto = new DonateResponseDto();
		donateResponseDto.setUserId(user.getUserId());
		donateResponseDto.setName(user.getName());
		donateResponseDto.setPanNumber(user.getPanNumber());
		donateResponseDto.setMobile(user.getMobile());
		donateResponseDto.setEmail(user.getEmail());

		donateResponseDto.setPaymentMode(userScheme.getPaymentMode());
		donateResponseDto.setDate(userScheme.getDate());
		donateResponseDto.setSchemeName(schemeResponse.get().getSchemeName());
		donateResponseDto.setDescription(schemeResponse.get().getDescription());
		donateResponseDto.setAmount(schemeResponse.get().getAmount());

		donateResponseDto.setTaxBenefitAmount(schemeResponse.get().getTaxBenefitAmount());
		donateResponseDto.setTaxBenefitDescription(schemeResponse.get().getTaxBenefitDescription());

		sendEmail(donateResponseDto);
		return donateResponseDto;
	}

	@Async
	public void sendEmail(DonateResponseDto donateResponseDto) {
		log.info("Entering into sendEmail of UserSchemeImpl");
		String message = "Dear ".concat(donateResponseDto.getName()).concat("Thank you for donating");
		sendMail.SendMailToDonor(donateResponseDto.getEmail(), "DONOR INVOICE", message);
	}
}
