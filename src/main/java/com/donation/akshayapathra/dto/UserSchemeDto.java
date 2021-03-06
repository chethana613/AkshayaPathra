package com.donation.akshayapathra.dto;

import java.time.LocalDate;

import com.donation.akshayapathra.common.AkshayapathraEnum.PaymentMode;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserSchemeDto {

	private String userName;
	private Integer schemeId;
	private String schemeName;
	private PaymentMode paymentMode;
	private String email;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate date;

}
