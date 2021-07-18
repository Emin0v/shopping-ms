package com.company.dto;

import com.company.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResDto {
    private String uuid;
    private List<OrderProductResDto> products;
    private String customerUuid;
    private Instant lastModifiedDate;
    private OrderStatus status;
    private String addressUuid;
    private BigDecimal totalAmount;
}