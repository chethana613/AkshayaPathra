package com.donation.akshayapathra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Admin {
	@Id
	private Integer adminId;
	private Long mobile;
	private String password;

}
