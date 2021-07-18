package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductCreateReqDto {

    @NotNull
    private String productUuid;

    @NotNull
    @Positive
    private Integer count;
}
