package com.donation.akshayapathra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.akshayapathra.constant.Constant;
import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.repository.SchemeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This class performs the operations related to the Available Schemes
 * 
 * @author yoga
 * @since Feb-14-2020
 * @version v1.0
 *
 */
@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	SchemeRepository schemeRepository;

	/**
	 * This method is used to get the available schemes
	 * 
	 * @author Yoga
	 * @return ListOfSchemes - Returns success/failure status code with message
	 * @throws SchemeNotFoundException
	 * @throws SchemesNotFoundException - thrown when Schemes are not available
	 * @since Feb-14-2020
	 */
	@Override
	public List<Scheme> viewAllDonations() throws SchemeNotFoundException {
		log.info("Entering into viewAllDonations of SchemeServiceImpl");
		List<Scheme> schemes = schemeRepository.findAll();
		if (schemes.isEmpty()) {
			log.info("Entering into schemes not available block in viewAllDonations of SchemeServiceImpl");
			throw new SchemeNotFoundException(Constant.SCHEME_NOT_FOUND_EXCEPTION);
		}
		return schemes;
	}

}
