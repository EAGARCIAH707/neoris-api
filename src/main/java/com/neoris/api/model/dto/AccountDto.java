package com.neoris.api.model.dto;

import com.neoris.api.model.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Integer accountId;

    private String number;

    private AccountType type;

    private BigDecimal initialAmount;

    private Boolean status;

    private Integer clientId;


}
