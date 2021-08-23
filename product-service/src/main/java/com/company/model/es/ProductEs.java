package com.company.model.es;

import com.company.model.MoneyTypes;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Document(indexName = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductEs implements Serializable {
    private String id;
    private String name;
    private String code;
    private String description;
    private CompanyEs seller;
    private String features;
    private CategoryEs category;
    private HashMap<MoneyTypes, BigDecimal> price;
    private List<String> images;
    private Boolean active;
}
