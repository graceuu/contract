package com.uprism.contract.domain.contract;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.uprism.contract.domain.company.Company;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)

@Entity
@Table(name = "contracts")
public class Contract {

	public static Contract of (
			final String name,
			final Company company,
			final String contactName,
			final String contact,
			final String email,
			final int maxLicense,
			final String startDate,
			final String contractDate,
			final BigDecimal totalPrice,
			final String remarks
			) {
		return new Contract();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@NonNull
	private Company company;
	
	@NonNull
	private String contactName;
	
	@NonNull
	private String contact;
	
	@NonNull
	private String email;
	
	private int maxLicense;
	
	@NonNull
	private LocalDateTime startDate;
	
	@NonNull
	private LocalDateTime contractDate;
	
	@NonNull
	private BigDecimal totalPrice;
	
	private String remarks;
}
