package com.uprism.contract.application.value;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ContractCommand {

    @Getter
    @Setter
    public static class Addition {

        private String companyName;
        private String companyEmail;
        private List<Payment> payments;
        private int maxLicense;
        private String contractDate;
        private BigDecimal totalPrice;
        private String remarks;

        @Getter
        @Setter
        public static class Payment {
            private BigDecimal price;
            private String content;
            private String remarks;
        }
    }
    
    @Getter
    @Setter
    public static class Modification {
    	
    	private Long id;
    	private String name;
    	private String contactName;
        private String contact;
        private String email;
        private String startDate;
        private String registrationNumber;
        private String ceoName;
        private String address;
    }
}
