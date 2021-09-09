package com.company.service;

import com.company.dto.OrderCreateReqDto;
import com.company.dto.OrderResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    String create(OrderCreateReqDto reqDto);

    void cancel(String uuid);

    void finish(String uuid);

    Page<OrderResDto> search(int pageNo, int pageSize);
}
