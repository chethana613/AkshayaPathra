package com.donation.akshayapathra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private String paymentMode;
	private String paymentStatus;
	private Byte taxObject;
}
