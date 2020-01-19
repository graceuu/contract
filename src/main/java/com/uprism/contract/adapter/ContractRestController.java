package com.uprism.contract.adapter;

import com.uprism.contract.application.ContractCommandService;
import com.uprism.contract.application.ContractQueryService;
import com.uprism.contract.application.value.ContractCommand;
import com.uprism.contract.application.value.ContractRepresentation;
import com.uprism.contract.domain.contract.Contract;
import com.uprism.contract.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/contract")
public class ContractRestController {

    private final ContractCommandService contractCommandService;
    private final ContractQueryService contractQueryService;

    @PostMapping
    public Response<ContractRepresentation.List> createContract(@RequestBody final ContractCommand.Addition command) {
        final Contract contract = contractCommandService.addContract(
                command.getName(),
                command.getCompanyName(),
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

        return new Response<>(
                ContractRepresentation.List
                        .builder()
                        .companyName(contract.getCompany().getName())
                        .build()
        );
    }

    @GetMapping
    public Response<List<ContractRepresentation.List>> getContracts() {
        final List<Contract> contracts = contractQueryService.getContracts();
        final List<ContractRepresentation.List> contractList = new ArrayList<>();

        for (Contract contract : contracts) {
            contractList.add(
                    ContractRepresentation.List
                            .builder()
                            .companyName(contract.getCompany().getName())
                            .contractDate(contract.getContractDate())
                            .totalPrice(contract.getTotalPrice())
                            .build()
            );
        }

        return new Response<>(contractList);
    }

    @GetMapping("/{id}")
    public Response<ContractRepresentation.Detail> getContract(@PathVariable("id") Long id) {
        final Contract contract = contractQueryService.getContract(id);

        return new Response<>(
                ContractRepresentation.Detail
                        .builder()
                        .build()
        );
    }
}
