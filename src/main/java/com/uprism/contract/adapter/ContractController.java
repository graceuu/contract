package com.uprism.contract.adapter;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uprism.contract.application.ContractCommandService;
import com.uprism.contract.application.ContractQueryService;
import com.uprism.contract.application.MailService;
import com.uprism.contract.application.value.ContractCommand;
import com.uprism.contract.application.value.ContractCommand.Addition;
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
    		HttpServletRequest request,
    		HttpSession session
    ) {
		final ContractCommand.Addition command = (Addition) session.getAttribute("contract");
        final Contract contract = contractCommandService.addContract(
                command.getCompanyName(),
                command.getCompanyEmail(),
                command.getMaxLicense(),
                command.getContractDate(),
                command.getTotalPrice(),
                command.getRemarks(),
                command.getPayments()
        );
        
        session.removeAttribute("contract");
        
        mailService.sendSimpleMail(
        		command.getCompanyEmail(),
        		"[uPrism io] 계약서를 작성해주세요!",
        		request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/contract/" + contract.getId()
        );
        
        return "redirect:";
    }
	
	@GetMapping
	public String getContracts(Model model) {
		List<Contract> contracts = contractQueryService.getContracts();
		
		model.addAttribute("contracts", contracts);
		return "contract/list";
	}
	
	@GetMapping("/{id}")
	public String getContract(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session
	) {
		final Contract contract = contractQueryService.getContract(id);
		
		model.addAttribute("contract", contract);
		
		if (session.getAttribute("user") == null && contract.getContactName() == null)
			return "contract/customerForm";
		
		return "contract/detail";
	}
	
	@PostMapping("/preview")
	public String preview(
			ContractCommand.Addition command,
			Model model,
			HttpSession session
	) {
		command.setTotalPrice(command.getPayments().stream().map(Payment::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
		session.setAttribute("contract", command);
		return "contract/preview";
	}
	
	@PostMapping("/update")
	public String updateContract(
			ContractCommand.Modification command
	) {
		contractCommandService.addContractAdditional(
				command.getId(),
				command.getName(),
				command.getContactName(),
				command.getContact(),
				command.getEmail(),
				command.getStartDate(),
				command.getRegistrationNumber(),
				command.getCeoName(),
				command.getAddress()
		);
		
		return "redirect:" + command.getId();
	}
	
	@GetMapping("/charts")
	public String getContractCharts() {
		
		return "contract/charts";
	}
}
