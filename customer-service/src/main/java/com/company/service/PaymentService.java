package com.company.service;

import com.company.dto.CardDto;
import com.company.dto.customer.PayReqDto;
import com.company.dto.customer.RollbackReqDto;

public interface PaymentService {

    void pay(PayReqDto reqDto);

    void rollback(RollbackReqDto reqDto);

    void top(CardDto reqDto);

}
