package com.uprism.contract.adapter;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uprism.contract.application.value.MemberCommand;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String loginPage() {
		return "login";
	}
	
	@PostMapping
	public String loginMember(
			final MemberCommand.Addition command,
			HttpSession session
	) {
		session.setAttribute("user", command.getId());
		return "redirect:/";
	}
}
