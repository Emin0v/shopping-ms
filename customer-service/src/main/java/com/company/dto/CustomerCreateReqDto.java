package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateReqDto {

    private String name;
    private String surname;
    private String username;
    private String password;
    private BigDecimal balance;
    private AddressCreateReqDto addressCreateReqDto;

}
