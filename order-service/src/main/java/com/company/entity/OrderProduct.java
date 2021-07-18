package com.company.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "order_products")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotNull
    @Column(nullable = false)
    private String productUuid;

    @NotNull
    @Column(nullable = false)
    @Positive
    private Integer count;

    @PrePersist
    public void prePersist(){
        setUuid(UUID.randomUUID().toString());
    }

}
