package com.uprism.contract.adapter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uprism.contract.application.value.MemberCommand;

@Controller
@RequestMapping("/regist")
public class RegistrationController {

	@GetMapping
	public String regist() {
		return "register";
	}
	
	@PostMapping("/member")
	public String registMember(final MemberCommand.Addition command) {
		System.out.println(command.getId());
		System.out.println(command.getName());
		System.out.println(command.getPassword());
		return "register";
	}
}
