package com.donation.akshayapathra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.akshayapathra.constant.Constant;
import com.donation.akshayapathra.dto.UserSchemeDto;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.service.SchemeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/schemes")
@CrossOrigin
@Slf4j
public class SchemeController {

	@Autowired
	SchemeService schemeService;

	
	/**
	 * @author PriyaDharshini S.
	 * @since 2020-02-14.
	 * 
	 *        This method will get list of the userScheme details by the scemeId.
	 * @param SchemeId - Id of the particular scheme
	 * @return  list of UserSchemeDto which has the list of userScemeDetails.
	 * @throws SchemeNotFoundException it will throw the exception if the SchemeId is
	 *                                 not there.
	 * 
	 */
	@GetMapping("/{schemeId}")
	public ResponseEntity<List<UserSchemeDto>> getUserSchemes(@PathVariable Integer schemeId)
			throws SchemeNotFoundException {
		if (ObjectUtils.isEmpty(schemeId)) {
			log.error("Exception occured in SchemeController getUserSchemes method:"+Constant.SCHEME_NOT_FOUND);
			throw new SchemeNotFoundException(Constant.SCHEME_NOT_FOUND);
		}
		log.info("Entering into SchemeController getUserSchemes method: calling schemeService");
		return new ResponseEntity<List<UserSchemeDto>>(schemeService.getUserSchemes(schemeId), HttpStatus.OK);
	}

}
