package com.company.dto;

import com.company.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateReqDto {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private BigDecimal balance;
    private AddressCreateReqDto addressCreateReqDto;

}
