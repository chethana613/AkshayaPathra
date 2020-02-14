package com.donation.akshayapathra.dto;

import java.time.LocalDate;

import com.donation.akshayapathra.common.AkshayapathraEnum.PaymentMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonateResponseDto extends ResponseDto{

	private Integer userId;
	private String name;
	private String panNumber;
	private Long mobile;
	private String email;
	private PaymentMode paymentMode;
	private LocalDate date;
	private String schemeName;
	private String description;
	private double amount;
	private double taxBenefitAmount;
	private String taxBenefitDescription;

}
