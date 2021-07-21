package com.company.messaging;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderNotification {

    private String customerUuid;
    private List<String> productUuid;
    private BigDecimal totalAmount;

}
