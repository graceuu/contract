package com.uprism.contract.adapter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uprism.contract.application.MemberCommandService;
import com.uprism.contract.application.value.MemberCommand;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/login")
public class LoginController {

	private final MemberCommandService memberCommandService;
	
	@GetMapping
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/member")
	public String loginMember(final MemberCommand.Addition command) {
		return "redirect:/";
	}
}
