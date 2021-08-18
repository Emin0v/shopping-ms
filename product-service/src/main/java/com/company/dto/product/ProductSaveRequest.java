package com.company.dto.product;

import com.company.model.MoneyTypes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class ProductSaveRequest {

    private String id;
    private String name;
    private String description;
    private String features;
    private BigDecimal available;
    private HashMap<MoneyTypes,BigDecimal> price;
    private List<String> images ;
    private String sellerId;
    private String categoryId;

}
