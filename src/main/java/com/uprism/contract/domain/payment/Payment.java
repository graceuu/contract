package com.uprism.contract.domain.payment;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.uprism.contract.domain.contract.Contract;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter

@Entity
@Table(name = "payments")
public class Payment {

	public Payment(@NotNull BigDecimal price, String content, String remarks, int orders) {
		this.price = price;
		this.content = content;
		this.remarks = remarks;
		this.orders = orders;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private BigDecimal price;

	private String content;

	private String remarks;

	private int orders;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Contract contract;
}
