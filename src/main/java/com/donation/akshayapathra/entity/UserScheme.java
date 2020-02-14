package com.donation.akshayapathra.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.donation.akshayapathra.common.AkshayapathraEnum.PaymentType;

import lombok.Data;

@Entity
@Data
public class UserScheme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userSchemeId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
	@ManyToOne
	@JoinColumn(name = "scheme_id")
	private Scheme schemeId;
	private PaymentType paymentMode;
	private String paymentStatus;
	private Byte taxInvoice;
	private LocalDate date;
}
