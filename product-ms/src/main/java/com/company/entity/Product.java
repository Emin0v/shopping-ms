package com.company.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @Positive
    @NotNull
    private BigDecimal price;

    @PrePersist
    public void prePersist(){
        setUuid(UUID.randomUUID().toString());
    }

}
