package com.company.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String name;
    private String code;
    private String description;
    private String companyId;
    private String features;
    private String categoryId;
    private Boolean active;
    private List<ProductImage> productImage;
    private HashMap<MoneyTypes, BigDecimal> price;
}
