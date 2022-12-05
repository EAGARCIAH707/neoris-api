package com.neoris.api.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
public class ReportDto {

    private Date date;

    private String client;

    private String account;

    private String accountType;

    private BigDecimal initialAmount;

    private Boolean status;

    private BigDecimal balance;

    public ReportDto(Date date, String client, String account, String accountType, BigDecimal initialAmount, Boolean status, BigDecimal balance) {
        this.date = date;
        this.client = client;
        this.account = account;
        this.accountType = accountType;
        this.initialAmount = initialAmount;
        this.status = status;
        this.balance = balance;
    }
}
