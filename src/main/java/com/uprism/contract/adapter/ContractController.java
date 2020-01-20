package com.uprism.contract.adapter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uprism.contract.application.ContractCommandService;
import com.uprism.contract.application.ContractQueryService;
import com.uprism.contract.application.MailService;
import com.uprism.contract.application.value.ContractCommand;
import com.uprism.contract.application.value.ContractCommand.Addition.Payment;
import com.uprism.contract.domain.contract.Contract;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/contract")
public class ContractController {
	
	private final ContractQueryService contractQueryService;
	private final ContractCommandService contractCommandService;
	private final MailService mailService;

	@GetMapping("/create")
	public String write() {
		return "contract/form";
	}
	
	@PostMapping("/create")
    public String createContract(
    		@RequestBody final ContractCommand.Addition command,
    		HttpServletRequest request
    ) {
        final Contract contract = contractCommandService.addContract(
                command.getName(),
                command.getCompanyName(),
                command.getCompanyEmail(),
                command.getContactName(),
                command.getContact(),
                command.getEmail(),
                command.getMaxLicense(),
                command.getStartDate(),
                command.getContractDate(),
                command.getTotalPrice(),
                command.getRemarks(),
                command.getPayments()
        );
        
        mailService.sendSimpleMail(
        		contract.getEmail(),
        		"[uPrism io] 계약서를 작성해주세요!",
        		request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + contract.getId()
        );
        
        return "redirect:";
    }
	
	@GetMapping
	public String getContracts(Model model) {
		List<Contract> contracts = contractQueryService.getContracts();
		
		model.addAttribute("contracts", contracts);
		return "tables";
	}
	
	@PostMapping("/preview")
	public String preview(
			ContractCommand.Addition command,
			Model model,
			HttpSession session
	) {
		command.setTotalPrice(command.getPayments().stream().map(Payment::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
		session.setAttribute("contract", command);
		model.addAttribute("contract", command);
		return "contract/preview";
	}
}
