package com.uprism.contract.application.value;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class ContractRepresentation {

    @Builder
    @Getter
    public static class List {

        private String companyName;
        private String contractDate;
        private BigDecimal totalPrice;
    }

    @Builder
    @Getter
    public static class Detail {
    	
    	private String companyName;
        private String contractDate;
        private BigDecimal totalPrice;
    }
}
