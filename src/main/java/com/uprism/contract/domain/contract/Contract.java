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

@Entity
@Table(name = "contracts")
public class Contract {

    public Contract(
            @NotNull String name,
            @NotNull Company company,
            @NotNull List<Payment> payments,
            @NotNull String contactName,
            @NotNull String contact,
            @NotNull String email,
            int maxLicense,
            @NotNull String startDate,
            @NotNull String contractDate,
            @NotNull BigDecimal totalPrice,
            String remarks
    ) {
        this.name = name;
        this.company = company;
        this.payments = payments;
        this.contactName = contactName;
        this.contact = contact;
        this.email = email;
        this.maxLicense = maxLicense;
        this.startDate = startDate;
        this.contractDate = contractDate;
        this.totalPrice = totalPrice;
        this.remarks = remarks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Company company;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.PERSIST)
    @NotNull
    private List<Payment> payments;

    @NotNull
    private String contactName;

    @NotNull
    private String contact;

    @NotNull
    private String email;

    private int maxLicense;

    @NotNull
    private String startDate;

    @NotNull
    private String contractDate;

    @NotNull
    private BigDecimal totalPrice;

    private String remarks;
}
