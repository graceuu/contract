package com.uprism.contract.adapter;

import com.uprism.contract.application.ContractCommandService;
import com.uprism.contract.application.ContractQueryService;
import com.uprism.contract.application.MailService;
import com.uprism.contract.application.value.ContractCommand;
import com.uprism.contract.application.value.ContractRepresentation;
import com.uprism.contract.application.value.ContractRepresentation.Detail.Company;
import com.uprism.contract.application.value.ContractRepresentation.Detail.Payment;
import com.uprism.contract.domain.contract.Contract;
import com.uprism.contract.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/contract")
public class ContractRestController {

    private final ContractCommandService contractCommandService;
    private final ContractQueryService contractQueryService;
    private final MailService mailService;

    @PostMapping
    public Response<ContractRepresentation.Detail> createContract(
    		@RequestBody final ContractCommand.Addition command,
    		HttpServletRequest request
    ) {
        final Contract contract = contractCommandService.addContract(
                command.getCompanyName(),
                command.getCompanyEmail(),
                command.getMaxLicense(),
                command.getContractDate(),
                command.getTotalPrice(),
                command.getRemarks(),
                command.getPayments()
        );
        
        mailService.sendSimpleMail(
        		command.getCompanyEmail(),
        		"[uPrism io] 계약서를 작성해주세요!",
        		request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/contract/" + contract.getId()
        );

        return new Response<>(
                ContractRepresentation.Detail
                        .builder()
                        .id(contract.getId())
                        .build()
        );
    }

    @GetMapping
    public Response<List<ContractRepresentation.Lists>> getContracts() {
        final List<Contract> contracts = contractQueryService.getContracts();
        final List<ContractRepresentation.Lists> contractList = new ArrayList<>();

        for (Contract contract : contracts) {
            contractList.add(
                    ContractRepresentation.Lists
                            .builder()
                            .contractId(contract.getId())
                            .companyName(contract.getCompany().getName())
                            .contractDate(contract.getContractDate())
                            .totalPrice(contract.getTotalPrice())
                            .build()
            );
        }

        return new Response<>(contractList);
    }

    @GetMapping("/{id}")
    public Response<ContractRepresentation.Detail> getContract(
    		@PathVariable("id") Long id
    ) {
        final Contract contract = contractQueryService.getContract(id);
        final Company company = ContractRepresentation.Detail.Company
        		.builder()
        		.id(contract.getCompany().getId())
        		.name(contract.getCompany().getName())
        		.email(contract.getCompany().getEmail())
        		.registrationNumber(contract.getCompany().getRegistrationNumber())
        		.ceoName(contract.getCompany().getCeoName())
        		.address(contract.getCompany().getAddress())
        		.build();
        final List<Payment> payments = new ArrayList<>();
        
        for (com.uprism.contract.domain.payment.Payment payment : contract.getPayments()) {
        	Payment paymentData = Payment
        			.builder()
        			.id(payment.getId())
        			.price(payment.getPrice())
        			.content(payment.getContent())
        			.remarks(payment.getRemarks())
        			.orders(payment.getOrders())
        			.build();
        	
        	payments.add(paymentData);
        }
        
        return new Response<>(
                ContractRepresentation.Detail
                        .builder()
                        .id(contract.getId())
                        .name(contract.getName())
                        .company(company)
                        .payments(payments)
                        .contactName(contract.getContactName())
                        .contact(contract.getContact())
                        .email(contract.getEmail())
                        .maxLicense(contract.getMaxLicense())
                        .startDate(contract.getStartDate())
                        .contractDate(contract.getContractDate())
                        .totalPrice(contract.getTotalPrice())
                        .remarks(contract.getRemarks())
                        .build()
        );
    }
    
    @PutMapping("/{id}")
    public Response<ContractRepresentation.Detail> updateContract(
    		@PathVariable("id") Long id,
    		@RequestBody final ContractCommand.Modification command
    ) {
        final Contract contract = contractCommandService.addContractAdditional(
        		id,
                command.getName(),
                command.getContactName(),
                command.getContact(),
                command.getEmail(),
                command.getStartDate(),
                command.getRegistrationNumber(),
                command.getCeoName(),
                command.getAddress()
        );
        
        return new Response<>(
                ContractRepresentation.Detail
                        .builder()
                        .id(contract.getId())
                        .build()
        );
    }
}
