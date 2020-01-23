package com.uprism.contract.adapter;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uprism.contract.application.ContractQueryService;
import com.uprism.contract.domain.contract.Contract;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
public class IndexController {

	private final ContractQueryService contractQueryService;
	
	@GetMapping
	public String indexPage(Model model) {
		List<Contract> contracts = contractQueryService.getContracts();
		
		model.addAttribute("contracts", contracts);
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login";
	}
}
