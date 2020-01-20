package com.uprism.contract.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uprism.contract.application.value.ContractCommand;
import com.uprism.contract.domain.company.Company;
import com.uprism.contract.domain.company.CompanyRepository;
import com.uprism.contract.domain.contract.Contract;
import com.uprism.contract.domain.contract.ContractRepository;
import com.uprism.contract.domain.payment.Payment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContractCommandService {

    private final ContractRepository contractRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Contract addContract(
            final String name,
            final String companyName,
            final String companyEmail,
            final String contactName,
            final String contact,
            final String email,
            final int maxLicense,
            final String startDate,
            final String contractDate,
            final BigDecimal totalPrice,
            final String remarks,
            final List<ContractCommand.Addition.Payment> payments
    ) {
        final Company company = companyRepository.findByName(companyName);

        final List<Payment> paymentList = new ArrayList<>();
        for (int i = 0; i < payments.size(); i++) {
            ContractCommand.Addition.Payment payment = payments.get(i);
            paymentList.add(
                    new Payment(payment.getPrice(), payment.getContent(), payment.getRemarks(), i)
            );
        }

        final Contract contract = contractRepository.save(
                new Contract(
                        name,
                        company == null ? Company.of(companyName, companyEmail) : company,
                        paymentList,
                        contactName,
                        contact,
                        email,
                        maxLicense,
                        startDate,
                        contractDate,
                        totalPrice,
                        remarks
                )
        );

        for (Payment payment : contract.getPayments()) {
            payment.setContract(contract);
        }


        return contract;
    }
}
