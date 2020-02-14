package com.donation.akshayapathra.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserSchemeDto {

	private Integer userId;
	private Integer schemeId;
	private String schemeName;
	private String paymentMode;
	private String paymentStatus;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate date;

}
