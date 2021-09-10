package com.company.service.impl;

import com.company.client.CustomerServiceClient;
import com.company.client.ProductServiceClient;
import com.company.dto.OrderCreateReqDto;
import com.company.dto.OrderProductCreateReqDto;
import com.company.dto.OrderResDto;
import com.company.dto.customer.PayReqDto;
import com.company.dto.product.ProductPriceResDto;
import com.company.entity.Order;
import com.company.entity.OrderProduct;
import com.company.exception.OrderNotFoundException;
import com.company.exception.OrderStatusException;
import com.company.repository.OrderRepository;
import com.company.security.auth.service.SecurityService;
import com.company.security.exception.UserNotFoundException;
import com.company.service.OrderNotificationService;
import com.company.service.OrderService;
import com.company.service.adapter.OrderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.entity.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderAdapter orderAdapter;
    private final SecurityService securityService;
    private final CustomerServiceClient customerServiceClient;
    private final ProductServiceClient productServiceClient;
    private final OrderNotificationService orderNotificationService;

    @Override
    @Transactional
    public String create(OrderCreateReqDto reqDto) {

        String currentUserUuid = securityService
                .getCurrentUserUuid().orElseThrow(UserNotFoundException::new);

        var orderBuilder = Order.builder()
                .customerUuid(currentUserUuid)
                .addressUuid(reqDto.getAddressUuid());

        Set<ProductPriceResDto> prices = productServiceClient.getProductPrice(
                reqDto
                        .getOrderProducts()
                        .stream()
                        .map(OrderProductCreateReqDto::getProductUuid)
                        .collect(Collectors.toSet())
        );

        BigDecimal sum = prices.stream().map(ProductPriceResDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        orderBuilder.products(
                prices
                        .stream()
                        .map(priceDto -> OrderProduct
                                .builder()
                                .productUuid(priceDto.getUuid())
                                .count(
                                        reqDto.getOrderProducts()
                                                .stream()
                                                .filter(product-> product.getProductUuid().equals(priceDto.getUuid()))
                                                .findFirst()
                                                .map(OrderProductCreateReqDto::getCount)
                                                .orElse(0)
                                )
                                .build())
                        .collect(Collectors.toList())
        );

        orderBuilder.totalAmount(sum);

        customerServiceClient.pay(new PayReqDto(sum));

        var saved = orderRepository.save(orderBuilder.build());

        return saved.getUuid();
    }

    @Override
    @Transactional
    public Page<OrderResDto> search(int pageNo, int pageSize) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);

        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return orderRepository
                .findAllByCustomerUuid(currentUserUuid, pageable)
                .map(orderAdapter::map);
    }

    @Override
    @Transactional
    public void cancel(String uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrderNotFoundException(uuid));

        if (!IN_PROCESS.equals(order.getStatus())) {
            throw new OrderStatusException(CANCELLED);
        }

        orderRepository.updateOrderStatus(uuid, CANCELLED, Instant.now());

    }

    @Override
    @Transactional
    public void finish(String uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrderNotFoundException(uuid));

        if (!IN_PROCESS.equals(order.getStatus())) {
            throw new OrderStatusException(CANCELLED);
        }

        orderRepository.updateOrderStatus(uuid, FINISHED, Instant.now());

    }
}
