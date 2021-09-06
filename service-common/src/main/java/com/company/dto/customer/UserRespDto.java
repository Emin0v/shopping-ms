package com.company.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDto {

    private String id;
    private String uuid;
    private String name;
    private String surname;
    private String email;
    private BigDecimal balance;

}
