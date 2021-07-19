package com.company.service;

import com.company.client.contract.CustomerResDto;
import com.company.dto.CustomerCreateReqDto;

import java.util.List;

public interface CustomerService {

    CustomerResDto create(CustomerCreateReqDto createReqDto);

    CustomerResDto update(String customerUuid,CustomerCreateReqDto createReqDto);

    CustomerResDto getByUuid(String uuid);

    void delete(String uuid);

    List<CustomerResDto> searchNameOrSurnameOrUsername(String name,String surname,String username);

}
