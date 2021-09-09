package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateReqDto {

    @Size(min = 1)
    @NotNull
    private List<OrderProductCreateReqDto> orderProducts;

    private String addressUuid;

}
