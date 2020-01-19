package com.uprism.contract.application;

import com.uprism.contract.domain.contract.Contract;
import com.uprism.contract.domain.contract.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractQueryService {

    private final ContractRepository contractRepository;

    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }

    public Contract getContract(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 계약입니다."));
    }
}
