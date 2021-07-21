package com.company.service.impl;

import com.company.entity.Order;
import com.company.messaging.OrderNotification;
import com.company.service.OrderNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableBinding(Source.class)
public class OrderNotificationServiceImpl implements OrderNotificationService {

    private final Source source;

    @Override
    public void sendNotificationToQueue(Order order) {
       OrderNotification orderNotification = OrderNotification.builder()
                .customerUuid(order.getCustomerUuid())
                .totalAmount(order.getTotalAmount())
                .productUuid(order.getProducts().stream().map(orderProduct -> {
                    return orderProduct.getProductUuid();
                }).collect(Collectors.toList()))
                .build();

       source.output().send(MessageBuilder.withPayload(orderNotification).build());

    }
}
