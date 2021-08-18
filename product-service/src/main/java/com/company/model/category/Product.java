package com.company.model.category;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(name = "title")
    @Basic(optional = false)
    private String title;

    @Column(name = "code")
    @Basic(optional = false)
    private String code;

    private String features;

    private Boolean active;

    @Column(name = "category_id")
    @Basic(optional = false)
    private String categoryId;

    @Column(name = "description")
    private String description;

    @SuppressWarnings("JpaAttributeTypeInspection")
    private HashMap<MoneyTypes, BigDecimal> price;

    @SuppressWarnings("JpaAttributeTypeInspection")
    private List<ProductImage> productImage;

    @PrePersist
    public void prePersist(){
        setUuid(UUID.randomUUID().toString());
    }

}
