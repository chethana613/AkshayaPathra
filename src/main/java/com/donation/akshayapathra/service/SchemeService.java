package com.donation.akshayapathra.service;

import java.util.List;

import com.donation.akshayapathra.entity.Scheme;
import com.donation.akshayapathra.exception.SchemeNotFoundException;

public interface SchemeService {

	public List<Scheme> viewAllDonations() throws SchemeNotFoundException;

}
