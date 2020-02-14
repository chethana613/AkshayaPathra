package com.donation.akshayapathra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.service.SchemeService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class performs the operations related to the Available Schemes
 * 
 * @author yoga
 * @since Feb-14-2020
 * @version v1.0
 *
 */
@RestController
@RequestMapping("/schemes")
@CrossOrigin
@Slf4j
public class SchemeController {

	@Autowired
	SchemeService schemeService;
	
	/**
	 * This method is used to get the available schemes
	 * 
	 * @author Yoga
	 * @return ListOfSchemes - Returns success/failure status code with message
	 * @throws SchemeNotFoundException 
	 * @throws SchemesNotFoundException   - thrown when Schemes are not available
	 * @since Feb-14-2020
	 */
	@GetMapping
	public ResponseEntity<List<Scheme>> viewAllDonations() throws SchemeNotFoundException{
	log.error("Entered into ViewAllDonations method in SchemeController ");
		return ResponseEntity.ok().body(schemeService.viewAllDonations());
	}
}
