package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    @NotBlank
    private String fullName;

    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotNull
    private LocalDate expDate;

    @NotNull
    private Integer securityCode;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private BigDecimal dummyCardBalance;
}
