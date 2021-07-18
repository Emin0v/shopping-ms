package com.company.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "orders")
public class Order extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @Size(min = 1)
    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderProduct> products ;

    @NotNull
    @Column(nullable = false)
    private String customerUuid;

    @NotNull
    @Column(nullable = false)
    private String addressUuid;

    @NotNull
    @Positive
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @PrePersist
    public void prePersist(){
        setUuid(UUID.randomUUID().toString());
        setStatus(OrderStatus.IN_PROCESS);
    }
}
