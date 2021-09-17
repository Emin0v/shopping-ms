package com.company.service;

import java.math.BigDecimal;

public interface CustomerService {

    void register(String uuid);

    BigDecimal getBalance(String userUuid);

}
