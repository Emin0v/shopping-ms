package com.company.service;

import com.company.dto.CustomerCreateReqDto;
import com.company.dto.CustomerResDto;

import java.util.List;

public interface CustomerService {

    CustomerResDto create(CustomerCreateReqDto createReqDto);

    CustomerResDto update(CustomerCreateReqDto createReqDto);

    CustomerResDto getById(Long id);

    void delete(Long id);

    List<CustomerResDto> searchNameOrSurnameOrUsername(String name,String surname,String username);

}
