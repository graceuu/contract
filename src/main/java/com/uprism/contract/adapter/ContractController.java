package com.uprism.contract.adapter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractController {

	@GetMapping("/contract")
	public String write() {
		return "blank";
	}
	
	@GetMapping("/preview")
	public String preview() {
		return "contract";
	}
}
