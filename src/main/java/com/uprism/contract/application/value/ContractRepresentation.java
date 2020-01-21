package com.uprism.contract.application.value;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public class ContractRepresentation {

    @Builder
    @Getter
    public static class Lists {

    	private Long contractId;
        private String companyName;
        private String contractDate;
        private BigDecimal totalPrice;
    }

    @Builder
    @Getter
    public static class Detail {
    	
    	private Long id;
    	private String name;
    	private Company company;
    	private List<Payment> payments;
    	private String contactName;
    	private String contact;
    	private String email;
    	private int maxLicense;
    	private String startDate;
    	private String contractDate;
        private BigDecimal totalPrice;
        private String remarks;
        
        @Builder
        @Getter
        public static class Company {
        	private Long id;
        	private String name;
        	private String email;
        	private String registrationNumber;
        	private String ceoName;
        	private String address;
        }
        
        @Builder
        @Getter
        public static class Payment {
        	private Long id;
        	private BigDecimal price;
        	private String content;
        	private String remarks;
        	private int orders;
        }
    }
}
