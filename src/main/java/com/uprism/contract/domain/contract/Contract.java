package com.uprism.contract.domain.contract;

import com.uprism.contract.domain.company.Company;
import com.uprism.contract.domain.payment.Payment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter

@Entity
@Table(name = "contracts")
public class Contract {

    public Contract(
            @NotNull Company company,
            @NotNull List<Payment> payments,
            int maxLicense,
            @NotNull String contractDate,
            @NotNull BigDecimal totalPrice,
            String remarks
    ) {
        this.company = company;
        this.payments = payments;
        this.maxLicense = maxLicense;
        this.contractDate = contractDate;
        this.totalPrice = totalPrice;
        this.remarks = remarks;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Company company;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.PERSIST)
    @NotNull
    private List<Payment> payments;

    private String contactName;

    private String contact;

    private String email;

    private int maxLicense;

    private String startDate;

    @NotNull
    private String contractDate;

    @NotNull
    private BigDecimal totalPrice;

    private String remarks;
}
