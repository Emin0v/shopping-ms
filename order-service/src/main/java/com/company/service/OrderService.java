package com.company.service;

import com.company.dto.OrderCreateReqDto;
import com.company.dto.OrderResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResDto create(OrderCreateReqDto reqDto);

    Page<OrderResDto> search(Pageable pageable);

    void cancel(String uuid);

    void finish(String uuid);
}
