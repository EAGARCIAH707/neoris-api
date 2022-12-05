package com.neoris.api.model.dto;

import com.neoris.api.model.enums.MovementType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class MovementDto {

    private Integer movementId;

    private Timestamp date;

    private MovementType type;

    @NotNull
    private BigDecimal value;

    private BigDecimal balance;

    @NotNull
    private Integer accountId;

}
