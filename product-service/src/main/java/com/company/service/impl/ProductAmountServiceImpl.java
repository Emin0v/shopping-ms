package com.company.service.impl;

import com.company.service.ProductAmountService;
import org.springframework.stereotype.Service;

@Service
public class ProductAmountServiceImpl implements ProductAmountService {

    @Override
    public int getByProductId(String productId) {
        return 10;
    }
}
