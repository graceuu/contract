package com.uprism.contract.application;

import org.springframework.stereotype.Service;

import com.uprism.contract.domain.member.Member;
import com.uprism.contract.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class MemberCommandService {

	private final MemberRepository memberRepository;
	
	public void addMember(final String id,
						final String name,
						final String password) {
		memberRepository.save(Member.of(id, name, password));
	}
}
