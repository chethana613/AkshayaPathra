package com.donation.akshayapathra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Scheme {
	@Id
	private Integer schemeId;
	private String schemeName;
	private String description;
	private double amount;
	private double taxBenefitAmount;
	private String taxBenefitDescription;
	private String imageUrl;

}
