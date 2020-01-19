package com.uprism.contract.application.value;

import com.uprism.contract.domain.company.Company;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class ContractCommand {

    @Getter
    @Setter
    public static class Addition {

        private String name;
        private String companyName;
        private List<Payment> payments;
        private String contactName;
        private String contact;
        private String email;
        private int maxLicense;
        private String startDate;
        private String contractDate;
        private BigDecimal totalPrice;
        private String remarks;

        @Getter
        public static class Payment {
            private BigDecimal price;
            private String content;
            private String remarks;
        }
    }
}
