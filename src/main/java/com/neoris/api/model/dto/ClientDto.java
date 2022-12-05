package com.neoris.api.model.dto;

import com.neoris.api.model.enums.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends PersonDto {

    private Integer clientCode;

    @NotBlank
    private String password;

    @NotNull
    private StateEnum status;
}
