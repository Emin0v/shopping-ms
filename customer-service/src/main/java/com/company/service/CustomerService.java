package com.company.service;

import com.company.utilities.results.Result;

import java.math.BigDecimal;

public interface CustomerService {

    Result register(String uuid);

    BigDecimal getBalance(String userUuid);

}
