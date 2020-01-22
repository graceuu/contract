package com.uprism.contract.domain.member;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter

@Entity
@Table(name = "members")
public class Member {
	
	public Member(
			String id,
			String name,
			String password
	) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	@Id
	private String id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String password;
}
