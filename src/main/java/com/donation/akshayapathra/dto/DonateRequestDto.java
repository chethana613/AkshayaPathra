package com.donation.akshayapathra.dto;

import javax.validation.constraints.NotNull;

import com.donation.akshayapathra.common.AkshayapathraEnum.PaymentMode;

import lombok.Data;

@Data
public class DonateRequestDto {

	@NotNull(message="userSchemeId cannot be null")
	private Integer schemeId;
	@NotNull(message="name cannot be null")
	private String name;
	@NotNull(message="panNumber cannot be null")
	private String panNumber;
	@NotNull(message="mobile cannot be null")
	private Long mobile;
	@NotNull(message="email cannot be null")
	private String email;
	@NotNull(message="paymentMode cannot be null")
	private PaymentMode paymentMode;
}
