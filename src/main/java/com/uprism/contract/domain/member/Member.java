package com.uprism.contract.domain.member;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)

@Entity
@Table(name = "members")
public class Member {
	
	public static Member of(final String id, final String name, final String password) {
		return new Member(id, name, password);
	}

	@Id
	@NonNull
	private String id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String password;
}
