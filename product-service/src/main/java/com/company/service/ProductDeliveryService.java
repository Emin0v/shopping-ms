package com.company.service;

import com.company.model.MoneyTypes;

import java.math.BigDecimal;

public interface ProductDeliveryService {

    String getDeliveryInfo(String productId);

    boolean freeDeliveryCheck(String productId, BigDecimal price, MoneyTypes moneyType);
}
