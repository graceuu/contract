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
@RequestMapping("/regist")
public class RegistrationController {

	private final MemberCommandService memberCommandService;
	
	@GetMapping
	public String regist() {
		return "register";
	}
	
	@PostMapping("/member")
	public String registMember(final MemberCommand.Addition command) {
		memberCommandService.addMember(
				command.getId(),
				command.getName(),
				command.getPassword()
		);
		return "redirect:/login";
	}
}
