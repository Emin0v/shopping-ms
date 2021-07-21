package com.company.service.impl;

import com.company.client.CustomerServiceClient;
import com.company.dto.OrderCreateReqDto;
import com.company.dto.OrderResDto;
import com.company.entity.Order;
import com.company.exception.OrderNotFoundException;
import com.company.exception.OrderStatusException;
import com.company.repository.OrderRepository;
import com.company.service.OrderNotificationService;
import com.company.service.OrderService;
import com.company.service.adapter.OrderAdapter;
import com.company.service.adapter.OrderProductAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.Collectors;

import static com.company.entity.OrderStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductAdapter orderProductAdapter;
    private final OrderAdapter orderAdapter;
    private final CustomerServiceClient customerServiceClient;
    private final OrderNotificationService orderNotificationService;

    @Override
    public OrderResDto create(OrderCreateReqDto reqDto) {

        String uuid = customerServiceClient.getByUuid(reqDto.getCustomerUuid()).getBody().getId();

        Order order = Order.builder()
                .addressUuid(reqDto.getAddressUuid())
                .products(reqDto.getOrderProducts()
                        .stream()
                        .map(orderProductAdapter::map)
                        .collect(Collectors.toList()))
                .customerUuid(uuid)
                .totalAmount(reqDto.getTotalAmount())
                .build();

        orderNotificationService.sendNotificationToQueue(order);


        return orderAdapter.map(orderRepository.save(order));
    }

    @Override
    public Page<OrderResDto> search(Pageable pageable) {
        return orderRepository
                .findAll(pageable)
                .map(orderAdapter::map);
    }

    @Override
    public void cancel(String uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrderNotFoundException(uuid));

        if (!IN_PROCESS.equals(order.getStatus())) {
            throw new OrderStatusException(CANCELLED);
        }

        orderRepository.updateOrderStatus(uuid, CANCELLED, Instant.now());

    }

    @Override
    public void finish(String uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrderNotFoundException(uuid));

        if (!IN_PROCESS.equals(order.getStatus())) {
            throw new OrderStatusException(CANCELLED);
        }

        orderRepository.updateOrderStatus(uuid, FINISHED, Instant.now());

    }
}
