package com.donation.akshayapathra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.akshayapathra.constant.Constant;
import com.donation.akshayapathra.dto.UserSchemeDto;
import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.entity.UserScheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;
import com.donation.akshayapathra.repository.SchemeRepository;
import com.donation.akshayapathra.repository.UserSchemeRepository;

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
public class SchemeServiceImpl implements SchemeService{
	
	@Autowired
	SchemeRepository schemeRepository;
	
	@Autowired
	UserSchemeRepository userSchemeRepository;
	
	
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
    public List<UserSchemeDto> getUserSchemes(Integer schemeId) throws SchemeNotFoundException{
    	Optional<Scheme> scheme = schemeRepository.findById(schemeId);
    	if(!scheme.isPresent()) {
    		log.error("Exception occured in SchemeServiceImpl getUserSchemes method:"+Constant.SCHEME_NOT_FOUND);
    		throw new SchemeNotFoundException(Constant.SCHEME_NOT_FOUND);
    	}
		log.info("Entering into SchemeServiceImpl getUserSchemes method: getting userScheme details");
    	List<UserScheme> userSchemes = userSchemeRepository.findAllBySchemeId(scheme.get());
    	List<UserSchemeDto> userSchemeDtos = new ArrayList<>();
    	userSchemes.forEach(userScheme -> {
    		UserSchemeDto userSchemeDto = new UserSchemeDto();
    		BeanUtils.copyProperties(userScheme, userSchemeDto);
    		userSchemeDto.setSchemeId(userScheme.getSchemeId().getSchemeId());
    		userSchemeDto.setSchemeName(userScheme.getSchemeId().getSchemeName());
    		userSchemeDto.setUserName(userScheme.getUserId().getName());
    		userSchemeDto.setEmail(userScheme.getUserId().getEmail());
    		userSchemeDtos.add(userSchemeDto);
    	});
    	return userSchemeDtos;
    }

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
