package com.neoris.api.model.dto;

import com.neoris.api.model.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Integer personId;

    @NotBlank
    private String name;

    @NotNull
    private GenderEnum gender;

    @NotNull
    private Integer age;

    @NotBlank
    private String identification;

    @NotBlank
    private String address;

    private String phone;

}
